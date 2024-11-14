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
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Aufkleber"))

    override fun getName(): String = "Stickers"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Wow, SpongeBob Aufkleber!", "Wow, SpongeBob Stickers!"))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            518,
            308
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Aufkleber", "Stickers")
}
