package org.wanne.game.stage

import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.model.animation.Animation
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PlayerState

class PointAndClickAwareStage(
    viewport: Viewport,
    poolAttendant: Player,
    duck: Player,
    additionalAnimations: List<Animation>?,
) : AbstractAnimationStage(
    viewport, poolAttendant, duck, additionalAnimations
) {
    // Im Singleplayer wird nur der FirstPlayerState benutzt und bei PLayer Wechsel die Figur gewechselt
    var firstPlayerState = PlayerState(player = poolAttendant)
    var secondPlayerState = PlayerState(player = duck)

    override fun draw() {
        // Bewegung ausrechnen
        movingPlayers(firstPlayerState)
        movingPlayers(secondPlayerState)

        drawTheWorld()
    }

    private fun movingPlayers(playerState: PlayerState) {
        if (playerState.moveToPoint != null && playerState.needToMove) {
            playerState.player.walkToPoint(
                playerState.moveToPoint!!.x,
                playerState.moveToPoint!!.y,
                playerState.lookingAtTheEnd,
                playerState.doTheAction,
            )
        }
    }
}
