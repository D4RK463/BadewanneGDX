package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
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
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(344),
            correctPositionY(264)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Eis!!!", "Ice cream!!!")

    override fun getSprite(time: Float): Sprite {
        return addPositionToSprite(inventoryAtlas.createSprite("EisInv"))
    }

    override fun getName(): String = "Ice"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Das sieht wirklich lecker aus.", "Looks yummy."))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        // ToDo: Endvideo einfügen
        println("Game Over")

        game.startedGame = false
        game.screen = game.mainMenuScreen
    }

}
