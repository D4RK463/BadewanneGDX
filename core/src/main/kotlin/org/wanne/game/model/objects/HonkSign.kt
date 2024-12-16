package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class HonkSign(
    posX: Float = 740F,
    posY: Float = 330F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posY)
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("HornySchild"))

    override fun getName(): String = "HonkSign"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(
            "'Honk, if you're horny'? ",
            game.choose("Ich bin ehr hungry!", "I am more like hungry!")
        )
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(game.choose(
            "Ne, ich glaub nicht das wir den brauchen.",
            "Nah, I don't think we gonna need that."
        ))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            correctPositionX(650F),
            correctPositionY(170F)
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = game.choose("Aufkleber", "Sticker")
}
