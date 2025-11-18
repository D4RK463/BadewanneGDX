package org.wanne.game.listener

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.Statistic
import org.wanne.game.model.ActionType
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.objects.GameObject
import org.wanne.game.model.player.Player
import org.wanne.game.network.NetworkManager
import org.wanne.game.network.model.ActionWrapper
import org.wanne.game.network.model.SerializableAction
import org.wanne.game.stage.PointAndClickAwareStage

class PointAndClickListener(
    val dialogBoard: DialogBoard,
    private val roomLimits: IntArray,
    private val networkManager: NetworkManager?
) : InputListener(), ExternalListener {
    override fun touchDown(
        event: InputEvent?,
        x: Float,
        y: Float,
        pointer: Int,
        button: Int,
    ): Boolean {
        val stage = event?.stage as PointAndClickAwareStage
        networkManager?.sendClick(SerializableAction.createFromPointAndClickAction(stage.currentAction, x, y))
        return internalClick(stage, stage.currentAction, x, y, pointer)
    }

    override fun externalClick(stage: Stage, action: ActionWrapper): Boolean {
        return internalClick(
            stage as PointAndClickAwareStage,
            action.pointAndClickAction!!,
            action.x,
            action.y,
            0,
            true
        )
    }

    private fun internalClick(
        stage: PointAndClickAwareStage,
        currentAction: PointAndClickAction,
        x: Float,
        y: Float,
        pointer: Int,
        isMultiplayer: Boolean = false
    ): Boolean  {
        if (pointer == 0) {
            Statistic.countClick()

            // Aktion
            if (currentAction.type != ActionType.NOTHING) {
            println("${currentAction.type} at $x:$y")

                // Das Objekt holen, auf welches geklickt wurde
                val hitObject = if (isMultiplayer) {
                    currentAction.clickedObject
                } else {
                    stage.hit(x, y, true)
                }
                println("Hit: $hitObject")
                if (hitObject is GameObject) {
                    currentAction.clickedObject = hitObject

                    // Wenn es sich um eine Kombinieren-Aktion handelt,
                    // soll nur gegangen werden, nachdem beide Objekte angeklickt wurden
                    if (currentAction.type == ActionType.COMBINE) {
                        if (currentAction.combineObject1 != null) {
                            maybeMove(hitObject, stage)
                        } else {
                            // Nur die Action ausführen
                            stage.needToMove = false
                            currentAction.action(dialogBoard)
                        }
                    } else {
                        maybeMove(hitObject, stage)
                    }
                } else if (hitObject is Image) { // Escape vom Dialog
                    currentAction.reset()

                    stage.lookingAtTheEnd = null
                    stage.doTheAction = {}
                    dialogBoard.reset()
                } else if (hitObject is Label) { // Im Dialog
                    currentAction.lastSentence = hitObject.text.toString()
                    stage.doTheAction = {
                        currentAction.action(dialogBoard)
                    }
                }
            } else { // oder laufen
                stage.lookingAtTheEnd = null
                stage.doTheAction = {}
                dialogBoard.reset()

                var moveX = x.toInt()
                var moveY = y.toInt()

                // Er darf sich nur bewegen, wenn der Klick innerhalb der Spiellimits liegt
                if (checkGameLimits(moveX, moveY)) {
                    if (moveX < roomLimits[0]) { // links
                        moveX = roomLimits[0]
                    } else if (moveX > roomLimits[2]) { // rechts
                        moveX = roomLimits[2]
                    }
                    if (moveY < roomLimits[1]) { // unten
                        moveY = roomLimits[1]
                    } else if (moveY > roomLimits[3]) { // oben
                        moveY = roomLimits[3]
                    }

                    // Koordinaten am Raster ausrichten
                    moveX = (moveX - (moveX % Player.MOVE_PIXEL))
                    moveY = (moveY - (moveY % Player.MOVE_PIXEL))

                    stage.moveToPoint = Point(moveX.toFloat(), moveY.toFloat())
                    stage.needToMove = true
                } else {
                    stage.currentPlayer.state = Player.Companion.State.STANDING
                    stage.needToMove = false
                }
            }
        } else {
            // Bei mehr als einem Finger
            setToBlackAndWhite(stage)
        }

        return true
    }

    private fun checkGameLimits(moveX: Int, moveY: Int): Boolean {
        return if (dialogBoard.game.classicMode()) {
            moveY in 129..685
        } else {
            moveY in 10..685 && moveX > 217
        }
    }

    private fun maybeMove(
        hitObject: GameObject,
        stage: PointAndClickAwareStage,
    ) {
        // Auf das Objekt zugehen und in die richtige Richtung schauen, wenn es nicht im Inventar ist
        if (!stage.currentAction.inventory.isObjectInInventory(hitObject)) {
            move(stage.currentAction.clickedObject, stage)

            // Es sei denn es ist eine Kombinieren-Aktion und das Item welches im Inventar ist, wird mit etwas
            // kombiniert, was noch angelaufen werden muss
        } else if (stage.currentAction.type == ActionType.COMBINE && stage.currentAction.combineObject1 != null) {
            // Wenn beide Objekte im Inventar sind, muss sich auch nicht bewegt werden
            if (stage.currentAction.inventory.isObjectInInventory(hitObject) &&
                stage.currentAction.isCombineObject1InTheInventory()
            ) {
                stage.doTheAction = {}
                stage.needToMove = false

                // Aktion sofort ausführen
                stage.currentAction.action(dialogBoard)
            } else {
                move(stage.currentAction.combineObject1, stage)
            }
        } else {
            stage.doTheAction = {}
            stage.needToMove = false

            // Aktion sofort ausführen
            stage.currentAction.action(dialogBoard)
        }
    }

    private fun move(
        actionObject: GameObject?,
        stage: PointAndClickAwareStage,
    ) {
        stage.moveToPoint = actionObject?.getInteractPosition()?.first
        stage.lookingAtTheEnd = actionObject?.getInteractPosition()?.second
        stage.needToMove = true

        // Aktion ausführen als Lambda, wenn der Spieler angekommen ist
        stage.doTheAction = {
            stage.currentAction.action(dialogBoard)
        }
    }

    override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
        val stage = event?.stage as PointAndClickAwareStage
        if (pointer > 0) {
            resetToColor(stage)
        }
    }

    override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
        val stage = event?.stage as PointAndClickAwareStage
        return when (keycode) {
            Input.Keys.SPACE -> {
                setToBlackAndWhite(stage)
                true
            }
            Input.Keys.W -> {
                val actors = stage.actors
                actors.filterIsInstance<GameObject>().forEach {
                    if (!it.isInInventory && it.isVisible) {
                        it.drunk = true
                    }
                }
                true
            }
            else -> {
                false
            }
        }
    }

    override fun keyUp(event: InputEvent?, keycode: Int): Boolean {
        val stage = event?.stage as PointAndClickAwareStage
        return when (keycode) {
            Input.Keys.SPACE -> {
                resetToColor(stage)
                true
            }
            Input.Keys.W -> {
                val actors = stage.actors
                actors.filterIsInstance<GameObject>().forEach {
                    it.drunk = false
                }
                true
            }
            else -> {
                false
            }
        }
    }

    private fun setToBlackAndWhite(stage: PointAndClickAwareStage) {
        val actors = stage.actors
        actors.filterIsInstance<GameObject>().forEach {
            if (!it.isInInventory && it.isVisible) {
                it.blackAndWhite = true
            }
        }
    }

    private fun resetToColor(stage: PointAndClickAwareStage) {
        val actors = stage.actors
        actors.filterIsInstance<GameObject>().forEach {
            it.blackAndWhite = false
        }
    }
}
