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
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            344,
            264
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Eis!!!"

    override fun getSprite(time: Float): Sprite {
        return addPositionToSprite(inventoryAtlas.createSprite("EisInv"))
    }

    override fun getName(): String = "Ice"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Das sieht wirklich lecker aus.")
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
