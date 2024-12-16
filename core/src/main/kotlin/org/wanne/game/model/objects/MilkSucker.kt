package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class MilkSucker(
    posX: Float = 302F,
    posY: Float = 270F,
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
            addPositionToSprite(inventoryAtlas.createSprite("MilchsaugerInv"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Milchsauger"))
        }

    override fun getName(): String = "MilkSucker"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(
            game.choose("Es ist der Milchabsauger 2000!", "It is the Milksucker 2000!"),
            game.choose("Netter Name für einen einfachen Eimer.", "Fancy name for a simple bucket.")
        )
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
            correctPositionX(414F),
            correctPositionY(264F)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Milchabsauger 2000", "Milksucker 2000")
}
