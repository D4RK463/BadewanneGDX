package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Scalpel(posX: Float = 174F, posY: Float = 411F, private val gameObjectToAppear: GameObject) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Skalpell"))

    override fun getName(): String = "Scalpel"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Das ist sogar scharf...krank.")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)

    override fun combine(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        if (action.setCombineObject(this)) {
            val combineObject = action.getCombineObjectByType("DrBear")

            if (combineObject != null) {
                combineObject.doCombineAction(dialogBoard, action)
                doCombineAction(dialogBoard, action)
            } else {
                action.reset()
            }
        }
    }

    override fun doCombineAction(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        action.inventory.removeGameObject(this)
        gameObjectToAppear.isVisible = true
        action.reset()
    }
}
