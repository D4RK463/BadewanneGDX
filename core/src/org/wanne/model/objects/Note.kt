package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Note(posX: Float = 200F, posY: Float = 200F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("TeleZettel"))
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Oh, eine Telefonnummer: 0190/******.")
    }

    override fun take(dialogBoard: DialogBoard, action: Action) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(344, 264), Player.Companion.Looking.LEFT)
    }
}
