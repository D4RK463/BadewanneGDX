package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class IceMenuRight(
    posX: Float = 647F,
    posY: Float = 411F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("EisRechts"))

    override fun getName(): String = "IceMenuRight"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Eis, Eis, Baby!...", "Lecker!")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            550,
            200
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Eiskarte"
}
