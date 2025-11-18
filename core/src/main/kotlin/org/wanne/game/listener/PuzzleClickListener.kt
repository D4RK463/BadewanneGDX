package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import org.wanne.game.Statistic
import org.wanne.game.model.puzzle.PuzzleAction
import org.wanne.game.model.puzzle.PuzzlePiece
import org.wanne.game.model.puzzle.VictoryMessage
import org.wanne.game.network.NetworkManager
import org.wanne.game.network.model.ActionWrapper
import org.wanne.game.stage.PuzzleStage

class PuzzleClickListener(
    private val networkManager: NetworkManager?
) : ClickListener(), ExternalListener {
    override fun clicked(
        event: InputEvent?,
        x: Float,
        y: Float,
    ) {
        val stage = event?.stage as PuzzleStage
        // ToDo: Netzwerkaktion nur bei Mehrspielerspielen
        internalClick(stage, stage.action, x, y)
    }

    override fun externalClick(
        stage: Stage,
        action: ActionWrapper
    ): Boolean {
        internalClick(stage as PuzzleStage, action.puzzleAction!!, action.x, action.y)
        return true
    }

    private fun internalClick(
        stage: PuzzleStage,
        currentAction: PuzzleAction,
        x: Float,
        y: Float,
    ) {
        Statistic.countClick()

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage.hit(x, y, true)

        if (hitObject is PuzzlePiece) {
            // Bei einem Doppelklick wird gedreht
            if (tapCount == 2) {
                currentAction.reset()
                hitObject.rotate90()

                // Sonst wird getauscht
            } else {
                if (currentAction.setChangePieces(hitObject)) {
                    stage.puzzle.exchangePieces(currentAction)
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
}
