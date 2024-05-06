package org.wanne.model.`object`

import com.badlogic.gdx.graphics.g2d.Sprite

class Window(posX: Float, posY: Float) : GameObject(posX, posY) {

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Fenster"))
    }
}