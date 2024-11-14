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
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Stroh"))

    override fun getName(): String = "Straw"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Stroh?", "Straw?"))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            862,
            216
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = choose("Stroh", "Straw")
}
