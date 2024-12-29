package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class DeanPoster(
    posX: Float = 20F,
    posY: Float = 435F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        initialize()
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("JamesDean"))

    override fun getName(): String = "DeanPoster"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(getLookName(), game.choose("Anspruchsvoll!", "Demanding!"))
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(
            game.choose("Geht nicht, das ist ein ", "I can't, that is a"),
            game.choose("tragendes Poster!", "load-bearing poster!")
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            correctPositionX(344F),
            correctPositionY(264F)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("James Dean Film", "James Dean Movie")
}
