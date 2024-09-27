package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Safe(
    posX: Float = 302F,
    posY: Float = 270F,
    am: AssetsManager
) : org.wanne.game.model.objects.GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Safe"))

    override fun getName(): String = "Safe"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Ein alter Safe mit einem Zahlenschloß...seltsam.")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            "Ich kenn die Zahlen nicht.",
            null,
            action,
        )
        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        doCombine(dialogBoard, action, this, "Stethoscope")
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        isVisible = false
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            414,
            264
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Alter Safe"
}
