package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.dialog.collections.CowDialogCollection
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.objects.CowState.NORMAL
import org.wanne.game.model.objects.CowState.NORMAL_END
import org.wanne.game.model.player.Player

class Cow(
    posX: Float = 1F,
    posY: Float = 1F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    private val dialogCollection = CowDialogCollection(am)

    private var state = NORMAL

    override fun getSprite(time: Float): Sprite = Sprite()

    override fun getName(): String = "Cow"

    override fun look(dialogBoard: DialogBoard) {
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> =
        Pair(Point(
            correctPositionX(posX.toInt()),
            correctPositionY(posY.toInt())
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Der Wächter", "The guardian")

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        val dialog = game.dialogManager.getFurtherDialogAndPlaySound(
            setStartSentenceAccordingToState(action.lastSentence),
            dialogCollection
        )
        dialogBoard.prepTalkTo(dialog, action)

        if (!game.possessWinningObjects) {
            when (state) {
                NORMAL -> {
                    if (dialog?.isStateChanged() == true) {
                        state = NORMAL_END
                        game.screen = game.roomScreen
                        action.reset()
                    }
                }

                NORMAL_END -> {
                    if (dialog?.isStateChanged() == true) {
                        game.screen = game.roomScreen
                        action.reset()
                    }
                }
            }
        } else {
            when (state) {
                NORMAL_END -> {
                    if (dialog?.isStateChanged() == true) {
                        game.cowIsBusy = true
                        game.screen = game.roomScreen
                        action.reset()
                    }
                }

                else -> {
                    throw IllegalStateException("Unmöglichen Dialog-Status erreicht")
                }
            }
        }
    }

    private fun setStartSentenceAccordingToState(sentence: String): String {
        if (sentence == "Start") {
            if (!game.possessWinningObjects) {
                if (state == NORMAL) {
                    return "Start1"
                }
                if (state == NORMAL_END) {
                    return "Start2"
                }
            } else {
                if (state == NORMAL_END) {
                    return "Start3"
                }
            }
        }

        return sentence
    }
}

enum class CowState {
    NORMAL,
    NORMAL_END
}
