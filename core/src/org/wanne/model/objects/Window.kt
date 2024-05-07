package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.player.Player

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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> {
        return Pair(Point(510,308), null)
    }
}