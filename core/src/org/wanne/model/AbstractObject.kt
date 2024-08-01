package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class AbstractObject(
    var posX: Float,
    var posY: Float,
) : Actor() {
    val itemAtlas: TextureAtlas = TextureAtlas("pictures/Items/items.atlas")

    abstract fun getSprite(): Sprite

    fun addPositionToSprite(sprite: Sprite): Sprite {
        sprite.x = posX
        sprite.y = posY
        return sprite
    }

    open fun dispose() {
    }

    open fun setPositionToPoint(point: Point) {
        posX = point.x.toFloat()
        posY = point.y.toFloat()
        x = posX
        y = posY
        positionChanged()
    }

    override fun draw(
        batch: Batch?,
        parentAlpha: Float,
    ) {
        val sprite = getSprite()
        sprite.setScale(scaleX, scaleY)
        sprite.draw(batch, parentAlpha)
    }

    override fun getWidth(): Float = getSprite().width

    override fun getHeight(): Float = getSprite().height
}
