package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Straw(
    posX: Float = 900F,
    posY: Float = 123F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Stroh"))

    override fun getName(): String = "Straw"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Stroh?")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(862, 216), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Stroh"
}
