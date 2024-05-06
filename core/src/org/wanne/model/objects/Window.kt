package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Window(posX: Float = 400F, posY: Float = 495F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Fenster"))
    }

    override fun look() {
        println("Ohh der Eismann!!!")
    }
}