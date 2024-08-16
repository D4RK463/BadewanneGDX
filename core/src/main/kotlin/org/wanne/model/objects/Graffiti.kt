package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Graffiti(
    posX: Float = 450F,
    posY: Float = 674F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Grafitti"))

    override fun getName(): String = "Graffiti"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Der Name kommt mir bekannt vor...")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(200, 220), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Graffiti"
}
