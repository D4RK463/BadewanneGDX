package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite

class Drawer(posX: Float = 400F, posY: Float = 359F) : GameObject(posX, posY) {

    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Schrank"))
    }

    override fun look() {
        println("Ein alter Schrank!!!")
    }
}