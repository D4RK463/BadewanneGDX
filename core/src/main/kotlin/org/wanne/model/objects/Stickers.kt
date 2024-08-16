package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Stickers(
    posX: Float = 418F,
    posY: Float = 432F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Aufkleber"))

    override fun getName(): String = "Stickers"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Wow, SpongeBob Aufkleber!")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(518, 308), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Aufkleber"
}
