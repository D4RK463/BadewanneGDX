package org.wanne.model.animation

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

class IcemanAnimation(posX: Float, posY: Float, visible: Boolean): org.wanne.model.animation.Animation(posX, posY, visible) {
    private val icemanHeadMove: TextureAtlas = TextureAtlas("pictures/Items/iceman.atlas")

    private val icemanAnimation = Animation(
        0.033f,
        icemanHeadMove.createSprites("iceman"),
        Animation.PlayMode.LOOP,
    )

    override fun getTextureRegionOfCurrentState(time: Float): TextureRegion? {
        return icemanAnimation.getKeyFrame(time)
    }

    fun getSpriteOfCurrentState(time: Float): Sprite {
        return icemanAnimation.getKeyFrame(time)
    }

}
