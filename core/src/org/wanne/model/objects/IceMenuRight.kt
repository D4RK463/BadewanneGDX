package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class IceMenuRight(
    posX: Float = 647F,
    posY: Float = 410F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("EisRechts"))

    override fun getName(): String = "IceMenuRight"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Eis, Eis, Baby!...")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(550, 200), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Eiskarte"
}
