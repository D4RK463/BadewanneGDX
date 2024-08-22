package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class GoldBag(
    posX: Float = 695F,
    posY: Float = 375F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Ein Sack voll Geld"

    override fun getSprite(time: Float): Sprite {
        return addPositionToSprite(inventoryAtlas.createSprite("GoldInv"))
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Kann das wirklich echtes Geld sein?")
    }

    override fun getName(): String = "GoldBag"

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        doCombine(dialogBoard, action, this, "Iceman")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt("Lecker, es ist Schoko-Geld!")
        action.reset()
    }

}
