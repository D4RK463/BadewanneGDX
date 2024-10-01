package org.wanne.game.stage

import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.model.animation.Animation
import org.wanne.game.model.player.Player

class MainMenuStage(
    viewport: Viewport,
    poolAttendant: Player,
    duck: Player,
    additionalAnimations: List<Animation>?
) : AbstractAnimationStage(
    viewport, poolAttendant, duck, additionalAnimations
) {

    override fun draw() {
        drawTheWorld()
    }
}
