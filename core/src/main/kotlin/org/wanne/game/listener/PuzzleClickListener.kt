package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import org.wanne.game.Statistic
import org.wanne.game.WanneGame
import org.wanne.game.model.ActionType
import org.wanne.game.model.puzzle.PuzzleAction
import org.wanne.game.model.puzzle.PuzzlePiece
import org.wanne.game.model.puzzle.VictoryMessage
import org.wanne.game.network.NetworkManager
import org.wanne.game.network.model.ActionWrapper
import org.wanne.game.network.model.SerializableAction
import org.wanne.game.stage.PuzzleStage

class PuzzleClickListener(
    private val networkManager: NetworkManager?,
    private val game: WanneGame
) : ClickListener(), ExternalListener {

    var isMultiplayerGame = networkManager != null

    override fun clicked(
        event: InputEvent?,
        x: Float,
        y: Float,
    ) {
        val stage = event?.stage as PuzzleStage
        internalClick(
            stage,
            stage.action,
            x,
            y,
            false)
    }

    // ToDo: Kann es sein das ich hier einfach 2 unterschiedliche Aktionen nehmen sollte?
    // Also komplett voneinander getrennt? Immerhin ist im Singleplayer die Action ein Singleton
    override fun externalClick(
        stage: Stage,
        action: ActionWrapper
    ): Boolean {
        internalClick(
            stage as PuzzleStage,
            action.puzzleAction!!,
            action.x,
            action.y,
            true)
        return true
    }

    private fun internalClick(
        stage: PuzzleStage,
        currentAction: PuzzleAction,
        x: Float,
        y: Float,
        isMultiplayerAction: Boolean
    ) {
        Statistic.countClick()

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage.hit(x, y, true)
        if (hitObject is PuzzlePiece) {
            var puzzleActionType: ActionType = ActionType.NOTHING

            // Bei einem Multiplayer-Spiel ist es wichtig zu wissen, welche Figur man spielt.
            // Die Ente kann drehen und der Bademeister verschieben.
            if (isMultiplayerGame) {
                if (game.player == 1) {
                    if (tapCount == 2) {
                        rotatePiece(currentAction, hitObject)
                    }
                } else {
                    exchangePieces(currentAction, hitObject)
                }

            } else {
                // Bei einem Doppelklick wird gedreht
                if (tapCount == 2) {
                    rotatePiece(currentAction, hitObject)
                    puzzleActionType = ActionType.ROTATE
                // Sonst wird getauscht
                } else {
                    exchangePieces(currentAction, hitObject)
                    puzzleActionType = ActionType.EXCHANGE
                }
            }

            if (!isMultiplayerAction) {
                networkManager?.sendClick(SerializableAction.createFromPuzzleAction(currentAction, x, y, puzzleActionType))
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

    private fun rotatePiece(currentAction: PuzzleAction, piece: PuzzlePiece) {
        currentAction.reset()
        piece.rotate90()
    }

    private fun exchangePieces(currentAction: PuzzleAction, piece: PuzzlePiece) {
        if (currentAction.setChangePieces(piece)) {
            val stage = piece.stage as PuzzleStage
            stage.puzzle.exchangePieces(currentAction)
        }
    }
}
