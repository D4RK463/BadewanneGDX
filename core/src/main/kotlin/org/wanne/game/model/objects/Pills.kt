package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Pills(
    posX: Float = 190F,
    posY: Float = 395F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite =
        if (isInInventory) {
            addPositionToSprite(inventoryAtlas.createSprite("TablettenInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Tabletten"))
        }

    override fun getName(): String = "Pills"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Valium, hartes Zeug für'n Teddy.", "Valium, hard stuff for that small bear."))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            choose("Gewinner nehmen keine Drogen!", "Winner don't do drugs!"),
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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            344,
            264
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Tabletten", "Pills")
}
