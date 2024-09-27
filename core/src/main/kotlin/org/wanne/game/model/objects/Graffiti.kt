package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Graffiti(
    posX: Float = 450F,
    posY: Float = 674F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Grafitti"))

    override fun getName(): String = "Graffiti"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Der Name kommt mir bekannt vor...")
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking?> = Pair(
        org.wanne.game.model.Point(
            200,
            220
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Graffiti"
}
