package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Note(
    posX: Float = 200F,
    posY: Float = 200F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posY)
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite =
        if (isInInventory) {
            addPositionToSprite(inventoryAtlas.createSprite("TeleZettelInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("TeleZettel"))
        }

    override fun getName(): String = "Note"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose(
            "Oh, eine Telefonnummer: 0190/******.",
            "Oh, it's a phone number: 0500/******."
        ))
    }

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
        doCombine(dialogBoard, action, this, "Telephone")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(344F),
            correctPositionY(264F)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Zettel mit Telefonnummer", "A Note with a number on it")
}
