package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Box(posX: Float = 678F, posY: Float = 272F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Kiste"))
    }

    override fun look() {
        println("Nur ne Kiste.")
    }
}