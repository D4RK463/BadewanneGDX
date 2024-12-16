package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Stickers(
    posX: Float = 418F,
    posY: Float = 432F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posY)
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Aufkleber"))

    override fun getName(): String = "Stickers"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("Wow, SpongeBob Aufkleber!", "Wow, SpongeBob Stickers!"))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(518F),
            correctPositionY(308F)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Aufkleber", "Stickers")
}
