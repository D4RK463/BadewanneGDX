package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.PointAndClickAction
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class DrBear(
    posX: Float = 187F,
    posY: Float = 388F,
    private val gameObjectToAppear: GameObject,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    private var broken = false

    override fun getSprite(): Sprite =
        if (broken) {
            addPositionToSprite(itemAtlas.createSprite("ArztbaerOffen"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Arztbaer"))
        }

    override fun getName(): String = "DrBear"

    override fun look(dialogBoard: DialogBoard) {
        if (broken) {
            dialogBoard.prepLookAt("Doktor Bär, 'leicht' lädiert.","Entschuldigung!")
        } else {
            dialogBoard.prepLookAt("Doktor Bär.","Ich hätte gern einen Termin für Sonntag :-)")
        }
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepTalkTo(
            "Arztbär: Eeeehhhhhehehehe!!",
            null,
            null,
            null,
            action,
        )
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        doCombine(dialogBoard, action, this, "Scalpel")
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        broken = true
        dialogBoard.prepLookAt("Arztbär: Uhhhhhh!!")
        gameObjectToAppear.isVisible = true
        action.reset()
    }
}
