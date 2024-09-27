package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Door(
    posX: Float = 960F,
    posY: Float = 154F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Tuer"))

    override fun getName(): String = "Door"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Eine verschlossene Tür. Aber warum ist die so groß?")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            "Ich kann sie nicht öffnen. Sie ist fest verschlossen.",
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            862,
            216
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Tür"
}
