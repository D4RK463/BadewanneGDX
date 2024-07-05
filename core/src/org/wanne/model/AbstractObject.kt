package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class AbstractObject(var posX: Float, var posY: Float): Actor() {

    abstract fun getSprite() : Sprite

    fun addPositionToSprite(sprite: Sprite): Sprite {
        sprite.x = posX
        sprite.y = posY
        return sprite
    }

    open fun dispose() {
    }

    fun setPositionToPoint(point: Point) {
        posX = point.x.toFloat()
        posY = point.y.toFloat()
        x = posX
        y = posY
        positionChanged()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        getSprite().draw(batch, parentAlpha)
    }

    override fun getWidth(): Float {
        return getSprite().width
    }

    override fun getHeight(): Float {
        return getSprite().height
    }
}