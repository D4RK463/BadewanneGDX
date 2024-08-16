package org.wanne.model.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.wanne.utils.GifDecoder

class CowCallAnimation(posX: Float, posY: Float, visible: Boolean = true): org.wanne.model.animation.Animation(posX, posY, visible) {
    private val fireAnimation = GifDecoder.loadGIFAnimation(
        Animation.PlayMode.LOOP,
        Gdx.files.internal("pictures/Backgrounds/Telephonezelle.gif").read()
    )

    override fun getTextureRegionOfCurrentState(time: Float): TextureRegion? {
        var texture: TextureRegion? = null

        if (visible) {
            texture = fireAnimation.getKeyFrame(time)
        }

        return texture
    }
}