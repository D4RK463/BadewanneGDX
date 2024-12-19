package org.wanne.game.stage

import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.animation.Animation
import org.wanne.game.model.player.Player

class PointAndClickAwareStage(
    viewport: Viewport,
    poolAttendant: Player,
    duck: Player,
    additionalAnimations: List<Animation>?,
) : AbstractAnimationStage(
    viewport, poolAttendant, duck, additionalAnimations
) {

    var currentPlayer: Player = poolAttendant

    var moveToPoint: Point? = null
    var needToMove = false

    var currentAction = PointAndClickAction.createDefaultAction()
    var lookingAtTheEnd: Player.Companion.Looking? = null
    var doTheAction: () -> Unit = {}

    @Deprecated("Wird bald entfernt")
    fun initializeInventoryItems() {
        // Alle Objekt die im Inventar sind, in die aktuelle Stage hinzufügen
        currentAction.inventory.items.forEach { addActor(it) }
    }

    override fun draw() {

        // Bewegung ausrechnen
        if (moveToPoint != null && needToMove) {
            currentPlayer.walkToPoint(
                moveToPoint!!.x,
                moveToPoint!!.y,
                lookingAtTheEnd,
                doTheAction,
            )
        }

        drawTheWorld()
    }
}
