package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.Player.Companion.Looking

class PoolAttendant(posX: Float, posY: Float, looking: Looking) : Player(posX, posY, looking) {
    private val scratchLeftTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Bademeister/scratchLeft.atlas")
    private val scratchRightTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Bademeister/scratchRight.atlas")
    private val lookLeftTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Bademeister/lookLeft.atlas")
    private val lookRightTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Bademeister/lookRight.atlas")
    private val walkLeftTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Bademeister/walkLeft.atlas")
    private val walkRightTextureAtlas: TextureAtlas =
        TextureAtlas("pictures/Players/Bademeister/walkRight.atlas")

    private var scratchLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            scratchLeftTextureAtlas.createSprites("meisterscratchL"),
            Animation.PlayMode.LOOP,
        )
    private var scratchRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            scratchRightTextureAtlas.createSprites("meisterscratchR"),
            Animation.PlayMode.LOOP,
        )
    private var lookLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            lookLeftTextureAtlas.createSprites("meisterlookL"),
            Animation.PlayMode.LOOP,
        )
    private var lookRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            lookRightTextureAtlas.createSprites("meisterlookR"),
            Animation.PlayMode.LOOP,
        )
    private var walkLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            walkLeftTextureAtlas.createSprites("meisterwalkL"),
            Animation.PlayMode.LOOP,
        )
    private var walkRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            walkRightTextureAtlas.createSprites("meisterwalkR"),
            Animation.PlayMode.LOOP,
        )

    override fun getSpriteOfCurrentState(stateTime: Float): Sprite {
        val sprite: Sprite

        if (looking == Looking.LEFT) {
            if (state == Companion.State.STANDING) {
                var spriteAni = scratchLeftAnimation.getKeyFrame(stateTime)
//                println(scratchLeftAnimation.isAnimationFinished(stateTime))
                if (scratchLeftAnimation.isAnimationFinished(stateTime)) {
                    spriteAni = lookLeftAnimation.getKeyFrame(stateTime)
                }

                sprite = spriteAni

//                if (scratchLeftAnimation!!.isAnimationFinished(stateTime)) {
//                    sprite = lookLeftAnimation!!.getKeyFrame(stateTime)
//                } else if (lookLeftAnimation!!.isAnimationFinished(stateTime)) {
//                    sprite = scratchLeftAnimation!!.getKeyFrame(stateTime)
//                } else {
//                    sprite = scratchLeftAnimation!!.getKeyFrame(stateTime)
//                }
            } else {
                sprite = walkLeftAnimation.getKeyFrame(stateTime, true)
            }
        } else {
            if (state == Companion.State.STANDING) {
                if (scratchRightAnimation.isAnimationFinished(stateTime)) {
                    sprite = lookRightAnimation.getKeyFrame(stateTime, true)
                } else if (lookRightAnimation.isAnimationFinished(stateTime)) {
                    sprite = scratchRightAnimation.getKeyFrame(stateTime)
                } else {
                    sprite = scratchRightAnimation.getKeyFrame(stateTime)
                }
            } else {
                sprite = walkRightAnimation.getKeyFrame(stateTime, true)
            }
        }

        sprite.x = posX
        sprite.y = posY

        return sprite
    }

    override fun getSprite(): Sprite {
        return lookLeftTextureAtlas.createSprite("meisterlookL", 1)
    }

    override fun dispose() {
        scratchLeftTextureAtlas.dispose()
        scratchRightTextureAtlas.dispose()
        lookLeftTextureAtlas.dispose()
        lookRightTextureAtlas.dispose()
        walkLeftTextureAtlas.dispose()
        walkRightTextureAtlas.dispose()
    }
}
