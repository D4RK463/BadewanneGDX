package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class GoldBag(
    posX: Float = 850F,
    posY: Float = 290F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posY)
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(800F),
            correctPositionY(234F)
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = game.choose("Ein Sack voll Geld", "A bag of money")

    override fun getSprite(time: Float): Sprite {
        return addPositionToSprite(inventoryAtlas.createSprite("GoldInv"))
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("Kann das wirklich echtes Geld sein?", "Can this really be real money?"))
    }

    override fun getName(): String = "GoldBag"

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this, !game.classicMode())
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
        dialogBoard.prepLookAt(game.choose("Lecker, es ist Schoko-Geld!", "Mhhh, chocolate coins!"))
        action.reset()
    }

}
