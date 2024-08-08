package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class IceMenuLeft(
    posX: Float = 491F,
    posY: Float = 472F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("EisLinks"))

    override fun getName(): String = "IceMenuLeft"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("So viele leckere Sorten. Wow!")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(400, 308), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Eiskarte"
}
