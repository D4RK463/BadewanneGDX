package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.dialog.StateChange
import org.wanne.game.dialog.collections.IcemanDialogCollection
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
    init {
        initialize()
    }

    private var icemanAnimation: IcemanAnimation? = null

    private var chosenWay: Way = Way.NONE

    private val dialogCollection = IcemanDialogCollection(am)

    private var iceGiven = false

    override fun initialize() {
        super.initialize()
        createAnimations()
    }

    private fun createAnimations() {
        icemanAnimation = IcemanAnimation(correctPositionX(posX), correctPositionY(posY), true, am)
    }

    override fun getSprite(time: Float): Sprite {
        if (icemanAnimation == null) {
            createAnimations()
        }
        val sprite = icemanAnimation!!.getSpriteOfCurrentState(1F)
        addPositionToSprite(sprite)
        return sprite
    }

    override fun draw(batch: Batch, parentAlpha: Float, time: Float) {
        icemanAnimation?.draw(batch, time)
    }

    override fun getName(): String = "Iceman"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(getLookName(), game.choose("Chilliger Dude!", "Cool dude!"))
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {

        val dialog = game.dialogManager.getFurtherDialogAndPlaySound(
            setStartSentenceAccordingToState(action.lastSentence),
            dialogCollection
        )
        dialogBoard.prepTalkTo(dialog, action)

        when (chosenWay) {
            Way.NONE -> {
                if (dialog?.isStateChanged() == true) {

                    // Freundlich sein
                    if (dialog.stateChange == StateChange.LEFT) {
                        chosenWay = Way.GOOD

                        // Eis ins Inventar packen
                        if (!gameObjectToAppear.isVisible) {
                            gameObjectToAppear.isVisible = true
                            action.inventory.addGameObjectToInventory(gameObjectToAppear, !game.classicMode())
                            iceGiven = true
                        }
                    }

                    // Unfreundlich sein
                    if (dialog.stateChange == StateChange.RIGHT) {
                        chosenWay = Way.BAD
                    }

                    action.reset()
                }
            }

            Way.GOOD -> {
                if (dialog?.isStateChanged() == true) {
                    action.reset()
                }
            }

            Way.BAD -> {
                action.reset()
            }
        }

    }

    private fun setStartSentenceAccordingToState(sentence: String): String {
        if (sentence == "Start") {
            return when (chosenWay) {
                Way.NONE -> {
                    "Start1"
                }

                Way.GOOD -> {
                    "Start2"
                }

                Way.BAD -> {
                    if (iceGiven) {
                        "Start4"
                    } else {
                        "Start3"
                    }
                }
            }
        }

        return sentence
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
            dialogBoard.prepLookAt(game.choose("Eismann: Jo man, mehr gibs nicht!", "Iceman: Jo man, thats all!"))
        } else {
            dialogBoard.prepLookAt(game.choose(
                "Jo man, nimmt das Ice und lass mich chilln, Duck-Boy!",
                "Jo man, take that and let me chill, Duck-Boy!"
            ))

            // Eis ins Inventar packen
            if (!gameObjectToAppear.isVisible) {
                gameObjectToAppear.isVisible = true
                action.inventory.addGameObjectToInventory(gameObjectToAppear, !game.classicMode())
                iceGiven = true
            }
        }

        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(524F),
            correctPositionY(234F)
        ), Player.Companion.Looking.RIGHT
    )

    override fun getToolTipDescription(): String = game.choose("Funky Eismann!", "Funky iceman!")

    private enum class Way {
        GOOD, BAD, NONE
    }

}
