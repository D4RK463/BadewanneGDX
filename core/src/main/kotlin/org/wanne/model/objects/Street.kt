package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Street(
    posX: Float = 0F,
    posY: Float = 538F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Strasse"))

    override fun getName(): String = "Street"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Wir können noch nicht gehen.", "Ich will Eis!")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            "Nein, ich will Eis!",
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(200, 220), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Straßenkreuzung"
}
