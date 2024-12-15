package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Scalpel(
    posX: Float = 174F,
    posY: Float = 411F,
    game: WanneGame,
    private val gameObjectToAppear: GameObject,
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
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
        dialogBoard.prepLookAt(game.choose("Das ist sogar scharf...krank.", "It's really sharp...sick."))
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
            correctPositionX(344),
            correctPositionY(264)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Skalpell", "Scalpel")

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
