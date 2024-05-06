package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Stickers(posX: Float = 418F, posY: Float = 432F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Aufkleber"))
    }

    override fun look() {
        println("Yey, der Schwamm!!")
    }
}