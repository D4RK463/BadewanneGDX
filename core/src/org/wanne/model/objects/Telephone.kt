package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Telephone(
    posX: Float = 745F,
    posY: Float = 366F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Telefon"))

    override fun getName(): String = "Telephone"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Super... rosa Telefon!")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        dialogBoard.prepUseIt(
            "Wen soll ich denn anrufen? Kenn keine Nummern.",
            null,
            action,
        )
        action.reset()
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
        doCombine(dialogBoard, action, this, "Note")
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        // Todo: Kuh anrufen Screen zeigen
        action.talkedToCow = true
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(650, 266), Player.Companion.Looking.RIGHT)
}
