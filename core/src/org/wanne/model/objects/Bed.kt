package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Bed(posX: Float = 65F, posY: Float = 210F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Bett"))
    }

    override fun look() {
        println("Ein Bett!!!")
    }
}