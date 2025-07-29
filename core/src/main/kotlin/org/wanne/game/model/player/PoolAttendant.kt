package org.wanne.game.model.player

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.game.ANIMATIONS
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.game.model.player.Player.Companion.Looking

class PoolAttendant(
    point: Point,
    looking: Looking,
    scaleX: Float = 1f,
    scaleY: Float = 1f,
    am: AssetsManager
) : Player(point.x, point.y, looking, scaleX, scaleY, am) {
    private val scratchLeftTextureAtlas: TextureAtlas = am["$ANIMATIONS/poolattendent/scratchLeft.atlas"]
    private val scratchRightTextureAtlas: TextureAtlas = am["$ANIMATIONS/poolattendent/scratchRight.atlas"]
    private val lookLeftTextureAtlas: TextureAtlas = am["$ANIMATIONS/poolattendent/lookLeft.atlas"]
    private val lookRightTextureAtlas: TextureAtlas = am["$ANIMATIONS/poolattendent/lookRight.atlas"]
    private val walkLeftTextureAtlas: TextureAtlas = am["$ANIMATIONS/poolattendent/walkLeft.atlas"]
    private val walkRightTextureAtlas: TextureAtlas = am["$ANIMATIONS/poolattendent/walkRight.atlas"]

    private var scratchLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            scratchLeftTextureAtlas.createSprites("meisterscratchL"),
            Animation.PlayMode.NORMAL,
        )
    private var scratchRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            scratchRightTextureAtlas.createSprites("meisterscratchR"),
            Animation.PlayMode.NORMAL,
        )
    private var lookLeftAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            lookLeftTextureAtlas.createSprites("meisterlookL"),
            Animation.PlayMode.NORMAL,
        )
    private var lookRightAnimation: Animation<Sprite> =
        Animation(
            0.033f,
            lookRightTextureAtlas.createSprites("meisterlookR"),
            Animation.PlayMode.NORMAL,
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

    private var currentAnimation: Animation<Sprite> = scratchRightAnimation
    private var stateTime = 0F

    override fun getSpriteOfCurrentState(time: Float): Sprite {
        val sprite: Sprite
        stateTime += time

        if (state == Companion.State.WALKING) {
            if (looking == Looking.LEFT) {
                sprite = walkLeftAnimation.getKeyFrame(stateTime, true)
            } else {
                sprite = walkRightAnimation.getKeyFrame(stateTime, true)
            }
        } else {
            val standingAnimation = currentAnimation

            if (looking == Looking.LEFT) {
                if (standingAnimation.isAnimationFinished(stateTime)) {
                    currentAnimation = lookLeftAnimation
                }
            } else {
                if (standingAnimation.isAnimationFinished(stateTime)) {
                    currentAnimation = lookRightAnimation
                }
            }

            sprite = standingAnimation.getKeyFrame(stateTime, true)
        }

        sprite.x = posX
        sprite.y = posY

        sprite.setScale(scaleX, scaleY)

        return sprite
    }

    override fun getSprite(): Sprite = lookLeftTextureAtlas.createSprite("meisterlookL", 1)

    override fun dispose() {
        scratchLeftTextureAtlas.dispose()
        scratchRightTextureAtlas.dispose()
        lookLeftTextureAtlas.dispose()
        lookRightTextureAtlas.dispose()
        walkLeftTextureAtlas.dispose()
        walkRightTextureAtlas.dispose()
    }

    override fun changeView(newView: Looking) {
        if (newView != looking) {
            if (newView == Looking.RIGHT) {
                currentAnimation = scratchRightAnimation
            } else {
                currentAnimation = scratchLeftAnimation
            }
            stateTime = 0F
        }

        this.looking = newView
    }
}
