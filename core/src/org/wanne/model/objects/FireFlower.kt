package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.player.Player

class FireFlower(posX: Float = 695F, posY: Float = 375F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    var solved = false

    override fun getSprite(): Sprite {
        return if (solved) {
            addPositionToSprite(itemAtlas.createSprite("Feuerblume"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("FeuerblumeKaputt"))
        }
    }

    override fun look() {
        println("Ganz schön heiß...aua!")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(650, 266), Player.Companion.Looking.RIGHT)
    }
}
