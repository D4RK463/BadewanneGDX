package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Inventory
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Ice(
    posX: Float = 695F,
    posY: Float = 375F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        initialize()
    }

    private val inventory: Inventory = Inventory.getInstance()

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(344F),
            correctPositionY(264F)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Eis!!!", "Ice cream!!!")

    override fun getSprite(time: Float): Sprite {
        return addPositionToSprite(inventoryAtlas.createSprite("EisInv"))
    }

    override fun getName(): String = "Ice"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("Das sieht wirklich lecker aus.", "Looks yummy."))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        // ToDo: Endvideo einfügen
        println("Game Over")

        inventory.clearInventory()
        game.arrivedOutside
        game.startedGame = false
        game.gameEnded = true
        game.screen = game.mainMenuScreen
    }

}
