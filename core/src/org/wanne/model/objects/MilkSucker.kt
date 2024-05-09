package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.player.Player

class MilkSucker(posX: Float = 302F, posY: Float = 270F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Milchsauger"))
    }

    override fun look() {
        println("Netter Name für einen einfachen Eimer.")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(414, 264), Player.Companion.Looking.LEFT)
    }
}
