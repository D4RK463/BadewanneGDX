package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Street(
    posX: Float = 0F,
    posY: Float = 538F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Strasse"))

    override fun getName(): String = "Street"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(
            choose("Wir können noch nicht gehen.", "We can't go now."),
            choose("Wir wollen Eis!", "We want ice!")
        )
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            choose("Nein, ich will Eis!", "No, I want ice cream!"),
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            correctPositionX(200),
            correctPositionY(220)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Straßenkreuzung", "Street corner")
}
