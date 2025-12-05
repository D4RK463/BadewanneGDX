package org.wanne.game.stage

import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.model.animation.Animation
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PlayerState
import org.wanne.game.model.player.PoolAttendant

class PointAndClickAwareStage(
    viewport: Viewport,
    val poolAttendant: Player,
    val duck: Player,
    additionalAnimations: List<Animation>?,
) : AbstractAnimationStage(
    viewport, poolAttendant, duck, additionalAnimations
) {

    // Im Singleplayer ist der erste Spieler immer der Bademeister
    var firstPlayer: Player = poolAttendant
    var secondPlayer: Player = duck

    var firstPlayerState: PlayerState = PlayerState(player = firstPlayer)
    var secondPlayerState: PlayerState = PlayerState(player = secondPlayer)

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

    fun switchPlayers() {
        if (firstPlayer is PoolAttendant) {
            firstPlayer = duck
            secondPlayer = poolAttendant
        } else {
            firstPlayer = poolAttendant
            secondPlayer = duck
        }

        firstPlayerState = PlayerState(player = firstPlayer)
        secondPlayerState = PlayerState(player = secondPlayer)
    }
}
