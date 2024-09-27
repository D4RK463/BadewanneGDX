package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import org.wanne.game.stage.PuzzleStage
import org.wanne.game.model.puzzle.PuzzlePiece
import org.wanne.game.model.puzzle.VictoryMessage

class PuzzleClickListener : ClickListener() {
    override fun clicked(
        event: InputEvent?,
        x: Float,
        y: Float,
    ) {
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
                stage.victoryMessage.isVisible = true
            }
        } else if (hitObject is org.wanne.game.model.puzzle.VictoryMessage) {
            stage.game.puzzleSolved = true
            stage.game.screen = stage.game.roomScreen
        }
    }
}
