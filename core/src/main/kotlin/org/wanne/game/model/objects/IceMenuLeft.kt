package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class IceMenuLeft(
    posX: Float = 492F,
    posY: Float = 473F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("EisLinks"))

    override fun getName(): String = "IceMenuLeft"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("So viele leckere Sorten. Wow!")
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking?> = Pair(
        org.wanne.game.model.Point(
            400,
            308
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Eiskarte"
}
