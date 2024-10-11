package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class BrucePoster(
    posX: Float = 695F,
    posY: Float = 510F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("BruceLee"))

    override fun getName(): String = "BrucePoster"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Der Typ ist mein Held.")
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            638,
            268
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Bruce Lee"
}
