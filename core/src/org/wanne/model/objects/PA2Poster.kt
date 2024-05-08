package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.player.Player

class PA2Poster(posX: Float = 196F, posY: Float = 532F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("PA2"))
    }

    override fun look() {
        println("Es ist von Projekt Ananas 2.")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(344, 264), Player.Companion.Looking.LEFT)
    }
}
