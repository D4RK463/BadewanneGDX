package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class BrucePoster(
    posX: Float = 695F,
    posY: Float = 510F,
) : GameObject(posX, posY) {
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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(638, 268), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Bruce Lee"
}
