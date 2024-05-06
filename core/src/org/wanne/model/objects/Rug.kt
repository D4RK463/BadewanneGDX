package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Rug(posX: Float = 278F, posY: Float = 190F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Teppich"))
    }

    override fun look() {
        println("Ein Teppich")
    }
}