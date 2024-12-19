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
        initialize()
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("EisRechts"))

    override fun getName(): String = "IceMenuRight"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(
            game.choose("Eis, Eis, Baby!...", "Ice, ice, baby!..."),
            game.choose("Lecker!", "Delicious!")
        )
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            correctPositionX(550F),
            correctPositionY(200F)
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = game.choose("Eiskarte", "Ice cream menu")
}
