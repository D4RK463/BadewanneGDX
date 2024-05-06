package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class PA2Poster(posX: Float = 196F, posY: Float = 532F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("PA2"))
    }

    override fun look() {
        println("Mein Lieblingsfilm")
    }
}