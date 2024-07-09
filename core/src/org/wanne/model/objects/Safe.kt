package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.PointAndClickAction
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Safe(
    posX: Float = 302F,
    posY: Float = 270F,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Safe"))

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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(414, 264), Player.Companion.Looking.LEFT)
}
