package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class AbstractObject(var posX: Float, var posY: Float): Actor() {

    val itemAtlas: TextureAtlas = TextureAtlas("pictures/Items/items.atlas")

    abstract fun getSprite() : Sprite

    fun addPositionToSprite(sprite: Sprite): Sprite {
        sprite.x = posX
        sprite.y = posY
        return sprite
    }

    fun dispose() {
        itemAtlas.dispose()
    }

    override fun getX(): Float {
        return posX
    }

    override fun getY(): Float {
        return posY
    }

    override fun setPosition(x: Float, y: Float) {
        posX = x
        posY = y
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