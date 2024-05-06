package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class BrucePoster(posX: Float = 695F, posY: Float = 510F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("BruceLee"))
    }

    override fun look() {
        println("Ich liebe den Typ")
    }
}