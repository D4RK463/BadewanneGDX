package org.wanne.game.model.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.utils.GifDecoder

class PowerUpAnimation(
    point: Point,
    visible: Boolean,
    am: AssetsManager
): org.wanne.game.model.animation.Animation(point.x, point.y, visible, am) {
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
