package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.animation.IcemanAnimation
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Iceman(
    posX: Float = 559F,
    posY: Float = 426F,
    game: WanneGame,
    private val gameObjectToAppear: GameObject,
) : GameObject(posX, posY, game) {
    private val icemanAnimation: IcemanAnimation  = IcemanAnimation(posX, posY, true, am)

    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    private var chosenWay: Way = Way.NONE

    private var iceGiven = false

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

                        // Eis ins Inventar packen
                        if (!gameObjectToAppear.isVisible) {
                            gameObjectToAppear.isVisible = true
                            action.inventory.addGameObjectToInventory(gameObjectToAppear)
                            iceGiven = true
                        }
                    }

                    "Mega gut, Danke." -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo man, Eis in the sunshine!",
                            null,
                            null,
                            null,
                            action,
                        )
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
                    }

                    "Ich schau mal in meiner Hose nach." -> {
                        dialogBoard.prepTalkTo(
                            "Eismann: Jo Ducky, whatever!",
                            null,
                            null,
                            null,
                            action,
                        )
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
                            null,
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

                if (iceGiven) {
                    dialogBoard.prepTalkTo(
                        "Eismann: Jo man, mehr gibs nicht!",
                        null,
                        null,
                        null,
                        action,
                    )
                } else {
                    dialogBoard.prepTalkTo(
                        "Eismann: Jo man, got the moneys?",
                        null,
                        null,
                        null,
                        action,
                    )
                }

                action.reset()
            }
        }

    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (chosenWay == Way.BAD) {
            doCombine(dialogBoard, action, this, "GoldBag")
        } else {
            super.combine(dialogBoard, action)
        }
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {

        if (iceGiven) {
            dialogBoard.prepLookAt("Eismann: Jo man, mehr gibs nicht!")
        } else {
            dialogBoard.prepLookAt("Jo man, nimmt das Ice und lass mich chilln, Duck-Boy!")

            // Eis ins Inventar packen
            if (!gameObjectToAppear.isVisible) {
                gameObjectToAppear.isVisible = true
                action.inventory.addGameObjectToInventory(gameObjectToAppear)
                iceGiven = true
            }
        }

        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            524,
            234
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Funky Eismann!"

    private enum class Way {
        GOOD, BAD, NONE
    }

}
