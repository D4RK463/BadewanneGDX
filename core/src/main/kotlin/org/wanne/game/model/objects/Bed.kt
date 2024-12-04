package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Bed(
    posX: Float = 65F,
    posY: Float = 210F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Bett"))

    override fun getName(): String = "Bed"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Ist das ein SuperSchaf?", "Is that a super-sheep?"))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            choose("Ich bin nicht müde.", "I am not tired."),
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            correctPositionX(344),
            correctPositionY(264)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Bett", "Bed")
}
