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

    var firstPlayer: Player = poolAttendant
    var secondPlayer: Player = duck

    var moveToPoint: Point? = null
    var needToMove = false
    var isMultiplayerAction = false

    var currentAction = PointAndClickAction.createDefaultAction()
    var lookingAtTheEnd: Player.Companion.Looking? = null
    var doTheAction: () -> Unit = {}

    override fun draw() {
        val player = if (isMultiplayerAction) secondPlayer else firstPlayer

        // Bewegung ausrechnen
        if (moveToPoint != null && needToMove) {
            player.walkToPoint(
                moveToPoint!!.x,
                moveToPoint!!.y,
                lookingAtTheEnd,
                doTheAction,
            )
        }

        drawTheWorld()
    }
}
