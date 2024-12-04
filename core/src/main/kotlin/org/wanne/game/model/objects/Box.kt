package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Box(
    posX: Float = 678F,
    posY: Float = 272F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Kiste"))

    override fun getName(): String = "Box"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Eine blaue Kiste, nix besonderes.", "A blue box, nothing special."))
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            correctPositionX(638),
            correctPositionY(268)
        ), Player.Companion.Looking.RIGHT
    )

    override fun getToolTipDescription(): String = choose("Blaue Kiste", "Blue box")
}
