package org.wanne.game.model.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.wanne.game.AssetsManager
import org.wanne.utils.GifDecoder

class CowCallAnimation(
    posX: Float,
    posY: Float,
    visible: Boolean = true,
    am: AssetsManager
): org.wanne.game.model.animation.Animation(posX, posY, visible, am) {
    private val cowAnimation = GifDecoder.loadGIFAnimation(
        Animation.PlayMode.LOOP,
        Gdx.files.internal("pictures/Backgrounds/Telephonezelle.gif").read()
    )

    override fun getTextureRegionOfCurrentState(time: Float): TextureRegion? {
        var texture: TextureRegion? = null

        if (visible) {
            texture = cowAnimation.getKeyFrame(time)
        }

        return texture
    }
}
