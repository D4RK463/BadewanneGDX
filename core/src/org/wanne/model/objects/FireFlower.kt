package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class FireFlower(
    posX: Float = 695F,
    posY: Float = 375F,
    private val gameObjectToManipulate: GameObject,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    var solved = false

    override fun getSprite(): Sprite =
        if (solved) {
            addPositionToSprite(itemAtlas.createSprite("Feuerblume"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("FeuerblumeKaputt"))
        }

    override fun getName(): String = "FireFlower"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Ganz schön heiß...aua!")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        // ToDo: Feuerblumen Puzzle starten
        solved = true
        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        if (solved && (gameObjectToManipulate is Mario) && !gameObjectToManipulate.talkForTheFirstTime) {
            doCombine(dialogBoard, action, this, "Mario")
        } else {
            super.combine(dialogBoard, action)
        }
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        action.inventory.removeGameObject(this)
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(650, 266), Player.Companion.Looking.RIGHT)
}
