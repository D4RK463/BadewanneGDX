package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Cowbell(
    posX: Float = 150F,
    posY: Float = 175F,
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
            addPositionToSprite(inventoryAtlas.createSprite("GlockeInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Glocke"))
        }

    override fun getName(): String = "Cowbell"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("Wie ist die hier her gekommen?", "How did that get here?"))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            game.choose("Erinnert mich an meinen Urlaub in den Bergen.", "Reminds me of my vacation in the alps."),
            null,
            action,
        )
        action.reset()
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this, !game.classicMode())
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            correctPositionX(288F),
            correctPositionY(154F)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Kuhglocke", "Cowbell")
}
