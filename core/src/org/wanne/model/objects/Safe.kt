package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Safe(posX: Float = 302F, posY: Float = 270F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Safe"))
    }

    override fun look() {
        println("Netter Safe")
    }
}