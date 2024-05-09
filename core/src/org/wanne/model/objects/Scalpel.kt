package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Scalpel(posX: Float = 174F, posY: Float = 411F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Skalpell"))
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Das ist sogar scharf...krank.")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(344, 264), Player.Companion.Looking.LEFT)
    }
}