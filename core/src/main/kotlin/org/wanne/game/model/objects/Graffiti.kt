package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Graffiti(
    posX: Float = 450F,
    posY: Float = 674F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        initialize()
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Grafitti"))

    override fun getName(): String = "Graffiti"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(getLookName(), game.choose("Der Name kommt mir bekannt vor...", "I heard that name before somewhere..."))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            correctPositionX(200F),
            correctPositionY(220F)
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Graffiti"
}
