package org.wanne.model.`object`

import com.badlogic.gdx.graphics.g2d.Sprite

class Drawer(posX: Float, posY: Float) : GameObject(posX, posY) {

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Schrank"))
    }
}