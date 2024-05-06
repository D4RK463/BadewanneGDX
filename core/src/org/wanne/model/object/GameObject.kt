package org.wanne.model.`object`

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class GameObject (var posX: Float, var posY: Float): Actor() {

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

}