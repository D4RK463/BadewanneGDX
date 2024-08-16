package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Pills(
    posX: Float = 190F,
    posY: Float = 395F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite =
        if (isInInventory) {
            addPositionToSprite(inventoryAtlas.createSprite("TablettenInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Tabletten"))
        }

    override fun getName(): String = "Pills"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Valium, hartes Zeug für'n Teddy.")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            "Gewinner nehmen keine Drogen!",
            null,
            action,
        )
        action.reset()
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Tabletten"
}
