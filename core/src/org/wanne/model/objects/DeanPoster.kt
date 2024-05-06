package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class DeanPoster(posX: Float = 20F, posY: Float = 435F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("JamesDean"))
    }

    override fun look() {
        println("Ich liebe den Typ auch")
    }
}