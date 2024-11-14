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
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("HornySchild"))

    override fun getName(): String = "HonkSign"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(
            "'Honk, if you're horny'? ",
            choose("Ich bin ehr hungry!", "I am more like hungry!")
        )
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(choose(
            "Ne, ich glaub nicht das wir den brauchen.",
            "Nah, I don't think we gonna need that."
        ))
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            650,
            170
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = choose("Aufkleber", "Sticker")
}
