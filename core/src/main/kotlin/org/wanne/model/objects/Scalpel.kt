package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Scalpel(
    posX: Float = 174F,
    posY: Float = 411F,
    private val gameObjectToAppear: GameObject,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite =
        if (isInInventory) {
            addPositionToSprite(inventoryAtlas.createSprite("SkalpellInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Skalpell"))
        }

    override fun getName(): String = "Scalpel"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Das ist sogar scharf...krank.")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Skalpell"

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        doCombine(dialogBoard, action, this, "DrBear")
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.removeGameObject(this)
        gameObjectToAppear.isVisible = true
        action.reset()
    }
}
