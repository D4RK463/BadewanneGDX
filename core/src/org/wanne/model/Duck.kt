package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.Player.Companion.Looking

class Duck(posX: Float, posY: Float, looking: Looking) : Player(posX, posY, looking) {
    private val lookLeftTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Ente/lookLeft.atlas")
    private val lookRightTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Ente/lookRight.atlas")
    private val walkLeftTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Ente/walkLeft.atlas")
    private val walkRightTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Ente/walkRight.atlas")

    private var lookLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            lookLeftTextureAtlas.createSprites("entelookL"),
            Animation.PlayMode.LOOP,
        )
    private var lookRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            lookRightTextureAtlas.createSprites("entelookR"),
            Animation.PlayMode.LOOP,
        )
    private var walkLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            walkLeftTextureAtlas.createSprites("entewalkL"),
            Animation.PlayMode.LOOP,
        )
    private var walkRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            walkRightTextureAtlas.createSprites("entewalkR"),
            Animation.PlayMode.LOOP,
        )

    override fun getSpriteOfCurrentState(stateTime: Float): Sprite {
        val sprite: Sprite =
            if (looking == Looking.LEFT) {
                if (state == Companion.State.STANDING) {
                    lookLeftAnimation.getKeyFrame(stateTime, true)
                } else {
                    walkLeftAnimation.getKeyFrame(stateTime, true)
                }
            } else {
                if (state == Companion.State.STANDING) {
                    lookRightAnimation.getKeyFrame(stateTime, true)
                } else {
                    walkRightAnimation.getKeyFrame(stateTime, true)
                }
            }

        sprite.x = posX
        sprite.y = posY

        return sprite
    }

    override fun getSprite(): Sprite {
        return lookLeftTextureAtlas.createSprite("entelookL", 1)
    }

    override fun dispose() {
        lookLeftTextureAtlas.dispose()
        lookRightTextureAtlas.dispose()
        walkLeftTextureAtlas.dispose()
        walkRightTextureAtlas.dispose()
    }
}
