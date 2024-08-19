package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.animation.IcemanAnimation
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Iceman(
    posX: Float = 559F,
    posY: Float = 426F,
    private val game: WanneGame,
) : GameObject(posX, posY) {
    private val icemanAnimation: IcemanAnimation  = IcemanAnimation(posX, posY, true)

    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    private var chosenWay: Way = Way.NONE

    override fun getSprite(time: Float): Sprite {
        val sprite = icemanAnimation.getSpriteOfCurrentState(time)
        addPositionToSprite(sprite)
        return sprite
    }

    override fun getName(): String = "Iceman"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Chilliger Dude!")
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {

        when (chosenWay) {
            Way.NONE -> {
                when (action.lastSentence) {
                    "Hell und funkelt! Und bei dir?" -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo man nice und shiny!",
                            null,
                            "Nice, kannst'e Eis entbehren?",
                            null,
                            action,
                        )
                    }

                    "Nice, kannst'e Eis entbehren?" -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo sure man! Take soviel,",
                            "wie ihr wollt, Bros, No need anyway!",
                            "Mega gut, Danke.",
                            null,
                            action,
                        )
                        chosenWay = Way.GOOD
                        action.reset()
                    }

                    "Wie steht die Sonne? Echt jetz?" -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo man chill!",
                            "Was wollt ihr?",
                            "Ich hätte gerne 1 großes Eis für meinen Freund hier.",
                            null,
                            action,
                        )
                    }

                    "Ich hätte gerne 1 großes Eis für meinen Freund hier." -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo man klar!",
                            "Got the money?",
                            "Ich schau mal in meiner Hose nach.",
                            null,
                            action,
                        )
                        chosenWay = Way.BAD
                        action.reset()
                    }

                    else -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo Duckboy und Bath-mann, ",
                            "wie steht die Sonne?",
                            "Wie steht die Sonne? Echt jetz?",
                            "Hell und funkelt! Und bei dir?",
                            action,
                        )
                    }
                }
            }
            Way.GOOD -> {
                when (action.lastSentence) {
                    "Danke, wir haben genug!" -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo man, Eis in the sunshine!",
                            null,
                            "... klar, was sonst?",
                            null,
                            action,
                        )
                        action.reset()
                    }
                    else -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo man, mehr Eis, Duckyboy?",
                            null,
                            "Danke, wir haben genug!",
                            null,
                            action,
                        )
                    }
                }
            }
            else -> {
                dialogBoard.prepTalkTo(
                    "Eismann: Jo man, got the moneys?",
                    null,
                    "Ich such noch!",
                    null,
                    action,
                )
                action.reset()
            }
        }

    }


    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(524, 234), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Funky Eismann!"

    private enum class Way {
        GOOD, BAD, NONE
    }

}
