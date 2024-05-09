package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.player.Player

class Teddy(posX: Float = 855F, posY: Float = 307F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    var sad = false

    override fun getSprite(): Sprite {
        return if (sad) {
            addPositionToSprite(itemAtlas.createSprite("TeddyTraurig"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Teddy"))
        }
    }

    override fun look() {
        println("So süß das man fast Karies davon kriegt.")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(806, 238), Player.Companion.Looking.RIGHT)
    }
}
