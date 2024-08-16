package org.wanne.model.player

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.player.Player.Companion.Looking

class Duck(
    posX: Float,
    posY: Float,
    looking: Looking,
    scaleX: Float = 1f,
    scaleY: Float = 1f,
) : Player(posX, posY, looking, scaleX, scaleY) {
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

    override fun getSpriteOfCurrentState(time: Float): Sprite {
        val sprite: Sprite =
            if (looking == Looking.LEFT) {
                if (state == Companion.State.STANDING) {
                    lookLeftAnimation.getKeyFrame(time, true)
                } else {
                    walkLeftAnimation.getKeyFrame(time, true)
                }
            } else {
                if (state == Companion.State.STANDING) {
                    lookRightAnimation.getKeyFrame(time, true)
                } else {
                    walkRightAnimation.getKeyFrame(time, true)
                }
            }

        sprite.x = posX
        sprite.y = posY

        sprite.setScale(scaleX, scaleY)

        return sprite
    }

    override fun getSprite(): Sprite = lookLeftTextureAtlas.createSprite("entelookL", 1)

    override fun dispose() {
        lookLeftTextureAtlas.dispose()
        lookRightTextureAtlas.dispose()
        walkLeftTextureAtlas.dispose()
        walkRightTextureAtlas.dispose()
    }
}
