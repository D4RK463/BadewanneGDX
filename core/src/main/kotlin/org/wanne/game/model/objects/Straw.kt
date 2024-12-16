package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Straw(
    posX: Float = 900F,
    posY: Float = 123F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posY)
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Stroh"))

    override fun getName(): String = "Straw"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("Stroh?", "Straw?"))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(862F),
            correctPositionY(216F)
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = game.choose("Stroh", "Straw")
}
