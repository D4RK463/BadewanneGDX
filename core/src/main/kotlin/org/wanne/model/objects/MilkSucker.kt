package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class MilkSucker(
    posX: Float = 302F,
    posY: Float = 270F,
) : GameObject(posX, posY) {
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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(414, 264), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Milchabsauger 2000"
}
