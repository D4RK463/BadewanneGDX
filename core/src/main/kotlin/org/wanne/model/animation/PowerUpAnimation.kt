package org.wanne.model.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.wanne.game.AssetsManager
import org.wanne.utils.GifDecoder

class PowerUpAnimation(
    posX: Float,
    posY: Float,
    visible: Boolean,
    am: AssetsManager
): org.wanne.model.animation.Animation(posX, posY, visible, am) {
    private val powerUpAnimation = GifDecoder.loadGIFAnimation(
        Animation.PlayMode.NORMAL,
        Gdx.files.internal("pictures/Items/Mario.gif").read()
    )

    override fun getTextureRegionOfCurrentState(time: Float): TextureRegion? {
        var texture: TextureRegion? = null

        if (visible) {
            texture = powerUpAnimation.getKeyFrame(time)
        }

        if (powerUpAnimation.isAnimationFinished(time)) {
            visible = false
        }

        return texture
    }

}
