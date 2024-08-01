package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.stage.PointAndClickAwareStage
import org.wanne.model.ActionType
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.objects.GameObject
import org.wanne.model.player.Player

class PointAndClickListener(
    val dialogBoard: DialogBoard,
) : InputListener() {
    override fun touchDown(
        event: InputEvent?,
        x: Float,
        y: Float,
        pointer: Int,
        button: Int,
    ): Boolean {
        val stage = event?.stage as PointAndClickAwareStage

        // Aktion
        if (stage.currentAction.type != ActionType.NOTHING) {
//            println("${stage.currentAction.type} at $x:$y")

            // Das Objekt holen, auf welches geklickt wurde
            val hitObject = stage.hit(x, y, true)
            if (hitObject is GameObject) {
                stage.currentAction.clickedObject = hitObject

                // Wenn es sich um eine Kombinieren-Aktion handelt, soll nur gegangen werden, nachdem beide Objekte angeklickt wurden
                if (stage.currentAction.type == ActionType.COMBINE) {
                    if (stage.currentAction.combineObject1 != null) {
                        maybeMove(hitObject, stage)
                    } else {
                        // Nur die Action ausführen
                        stage.needToMove = false
                        stage.currentAction.action(dialogBoard)
                    }
                } else {
                    maybeMove(hitObject, stage)
                }
            } else if (hitObject is Image) { // Escape vom Dialog
                stage.currentAction.reset()

                stage.lookingAtTheEnd = null
                stage.doTheAction = {}
                dialogBoard.reset()
            } else if (hitObject is Label) { // Im Dialog
                stage.currentAction.lastSentence = hitObject.text.toString()
                stage.doTheAction = {
                    stage.currentAction.action(dialogBoard)
                }
            }
        } else { // oder laufen
            stage.lookingAtTheEnd = null
            stage.doTheAction = {}
            dialogBoard.reset()

            // Raum Lauf-Limits
            val limits = IntArray(4)
            limits[0] = 255 // links
            limits[1] = 129 // unten
            limits[2] = 934 // rechts
            limits[3] = 312 // oben

            var moveX = x.toInt()
            var moveY = y.toInt()

            // Er darf sich nur bewegen, wenn der Klick innerhalb der Spiellimits liegt
            if (moveY in 129..685) {
                if (moveX < limits[0]) { // links
                    moveX = limits[0]
                } else if (moveX > limits[2]) { // rechts
                    moveX = limits[2]
                }
                if (moveY < limits[1]) { // unten
                    moveY = limits[1]
                } else if (moveY > limits[3]) { // oben
                    moveY = limits[3]
                }

                // Koordinaten am Raster ausrichten
                moveX = (moveX - (moveX % Player.MOVE_PIXEL))
                moveY = (moveY - (moveY % Player.MOVE_PIXEL))

                stage.moveToPoint = Point(moveX, moveY)
                stage.needToMove = true
            } else {
                stage.currentPlayer.state = Player.Companion.State.STANDING
                stage.needToMove = false
            }
        }

        return true
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
            // Wenn beide Objekte imprivate Inventar sind, muss sich auch nicht bewegt werden
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
}
