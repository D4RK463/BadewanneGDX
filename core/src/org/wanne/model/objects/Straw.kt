package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Straw(posX: Float = 900F, posY: Float = 123F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Stroh"))
    }

    override fun look() {
        println("Warum liegt da Stroh??")
    }
}