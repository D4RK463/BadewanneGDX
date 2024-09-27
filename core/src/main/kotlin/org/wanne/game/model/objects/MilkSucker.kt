package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class MilkSucker(
    posX: Float = 302F,
    posY: Float = 270F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite =
        if (isInInventory) {
            addPositionToSprite(inventoryAtlas.createSprite("MilchsaugerInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Milchsauger"))
        }

    override fun getName(): String = "MilkSucker"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Es ist der Milchabsauger 2000!", "Netter Name für einen einfachen Eimer.")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            414,
            264
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Milchabsauger 2000"
}
