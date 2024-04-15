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

    private var lookLeftAnimation: Animation<Sprite>? = null
    private var lookRightAnimation: Animation<Sprite>? = null
    private var walkLeftAnimation: Animation<Sprite>? = null
    private var walkRightAnimation: Animation<Sprite>? = null

    init {
        lookLeftAnimation =
            Animation(
                0.033f,
                lookLeftTextureAtlas.createSprites("entelookL"),
                Animation.PlayMode.LOOP,
            )

        lookRightAnimation =
            Animation(
                0.033f,
                lookRightTextureAtlas.createSprites("entelookR"),
                Animation.PlayMode.LOOP,
            )

        walkLeftAnimation =
            Animation(
                0.033f,
                walkLeftTextureAtlas.createSprites("entewalkL"),
                Animation.PlayMode.LOOP,
            )

        walkRightAnimation =
            Animation(
                0.033f,
                walkRightTextureAtlas.createSprites("entewalkR"),
                Animation.PlayMode.LOOP,
            )
    }

    override fun getSpriteOfCurrentState(stateTime: Float): Sprite {
        val sprite: Sprite

        if (looking == Looking.LEFT) {
            if (state == Companion.State.STANDING) {
                sprite = lookLeftAnimation!!.getKeyFrame(stateTime, true)
            } else {
                sprite = walkLeftAnimation!!.getKeyFrame(stateTime, true)
            }
        } else {
            if (state == Companion.State.STANDING) {
                sprite = lookRightAnimation!!.getKeyFrame(stateTime, true)
            } else {
                sprite = walkRightAnimation!!.getKeyFrame(stateTime, true)
            }
        }

        sprite.x = posX
        sprite.y = posY

        return sprite
    }

    override fun dispose() {
        lookLeftTextureAtlas.dispose()
        lookRightTextureAtlas.dispose()
        walkLeftTextureAtlas.dispose()
        walkRightTextureAtlas.dispose()
    }
}
