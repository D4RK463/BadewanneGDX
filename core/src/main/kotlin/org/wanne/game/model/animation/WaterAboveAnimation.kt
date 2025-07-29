package org.wanne.game.model.animation

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.wanne.game.ANIMATIONS
import org.wanne.game.AssetsManager

class WaterAboveAnimation(
    posX: Float,
    posY: Float,
    visible: Boolean,
    am: AssetsManager
): org.wanne.game.model.animation.Animation(posX, posY, visible, am) {
    private val waterFlowing: TextureAtlas = am["$ANIMATIONS/water_above.atlas"]

    val waterAnimation = Animation(
        0.099f,
        waterFlowing.createSprites("water_above"),
        Animation.PlayMode.LOOP,
    )

    override fun getTextureRegionOfCurrentState(time: Float): TextureRegion? {
        return waterAnimation.getKeyFrame(time)
    }

}
