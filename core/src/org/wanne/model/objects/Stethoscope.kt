package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Stethoscope(
    posX: Float = 206F,
    posY: Float = 424F,
    private val gameObjectToAppear: GameObject,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Stethoskop"))

    override fun getName(): String = "Stethoscope"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Alles was ein echter Arzt braucht.")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        doCombine(dialogBoard, action, this, "Safe")
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        isVisible = false
        action.inventory.removeGameObject(this)

        gameObjectToAppear.isVisible = true
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)
}
