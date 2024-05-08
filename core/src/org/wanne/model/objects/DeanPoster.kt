package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.player.Player

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
        println("Anspruchsvoll!")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(344, 264), Player.Companion.Looking.LEFT)
    }
}
