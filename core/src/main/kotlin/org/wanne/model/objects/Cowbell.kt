package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Cowbell(
    posX: Float = 150F,
    posY: Float = 175F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite =
        if (isInInventory) {
            addPositionToSprite(inventoryAtlas.createSprite("GlockeInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Glocke"))
        }

    override fun getName(): String = "Cowbell"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Wie ist die hier her gekommen?")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            "Erinnert mich an meinen Urlaub in den Bergen.",
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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(288, 154), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Kuhglocke"
}
