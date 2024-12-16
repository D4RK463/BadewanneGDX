package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class IceMenuLeft(
    posX: Float = 492F,
    posY: Float = 473F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posY)
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("EisLinks"))

    override fun getName(): String = "IceMenuLeft"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("So viele leckere Sorten. Wow!", "So many flavours. Wow!"))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            correctPositionX(400F),
            correctPositionY(308F)
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = game.choose("Eiskarte", "Ice cream menu")
}
