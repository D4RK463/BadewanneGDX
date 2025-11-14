package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import org.wanne.game.Statistic
import org.wanne.game.model.puzzle.PuzzlePiece
import org.wanne.game.model.puzzle.VictoryMessage
import org.wanne.game.network.SerializablePointAndClickAction
import org.wanne.game.stage.PuzzleStage

class PuzzleClickListener : ClickListener(), ExternalListener {
    override fun clicked(
        event: InputEvent?,
        x: Float,
        y: Float,
    ) {
        Statistic.countClick()
        val stage = event?.stage as PuzzleStage

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage.hit(x, y, true)

        if (hitObject is PuzzlePiece) {
            // Bei einem Doppelklick wird gedreht
            if (tapCount == 2) {
                stage.action.reset()
                hitObject.rotate90()

            // Sonst wird getauscht
            } else {
                if (stage.action.setChangePieces(hitObject)) {
                    stage.puzzle.exchangePieces(stage.action)
                }
            }

            if (stage.puzzle.isPuzzleSolved()) {
                // ToDo: Play victory sound!
                val victoryMessage = stage.actors.items.filterIsInstance<VictoryMessage>().first()
                victoryMessage.isVisible = true

                val exitButton = stage.actors.items.filterIsInstance<ImageButton>().first()
                exitButton.isVisible = false

                stage.actors.items.filterIsInstance<Label>().forEach {it.isVisible = false}
            }
        } else if (hitObject is VictoryMessage) {
            stage.game.puzzleSolved = true
            stage.game.screen = stage.game.roomScreen
        }
    }

    override fun externalClick(
        stage: Stage,
        action: SerializablePointAndClickAction
    ): Boolean {
        TODO("Not yet implemented")
    }
}
