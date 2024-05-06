package org.wanne.model.`object`

import com.badlogic.gdx.graphics.g2d.Sprite

class Bed(posX: Float, posY: Float) : GameObject(posX, posY), Lookable {

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Bett"))
    }

    override fun look() {
        println("Ein Bett!!!")
    }

}