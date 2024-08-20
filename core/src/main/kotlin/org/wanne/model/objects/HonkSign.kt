package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class HonkSign(
    posX: Float = 740F,
    posY: Float = 330F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("HornySchild"))

    override fun getName(): String = "HonkSign"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("'Honk, if you're horny'? ", "Ich bin ehr hungry!")
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt("Ne, ich glaub nicht das wir den brauchen. ")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(650, 170), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Aufkleber"
}
