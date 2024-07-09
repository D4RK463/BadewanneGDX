package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.PointAndClickAction
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player
import kotlin.system.exitProcess

class Telephone(
    posX: Float = 745F,
    posY: Float = 366F,
    val winningRequiredGameObjectList: List<GameObject>,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Telefon"))

    override fun getName(): String = "Telephone"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Super... rosa Telefon!")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (action.talkedToCow) {
            dialogBoard.prepUseIt(
                "Ich kenne die Nummer vom Wächter nicht auswendig.",
                null,
                action,
            )
        } else {
            dialogBoard.prepUseIt(
                "Wen soll ich denn anrufen? Kenn keine Nummern.",
                null,
                action,
            )
        }

        action.reset()
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        action.inventory.addGameObjectToInventory(this)
        action.reset()
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        doCombine(dialogBoard, action, this, "Note")
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        // Todo: Kuh anrufen Screen zeigen

        // Wenn wir schon zum 2ten Mal anrufen
        if (action.talkedToCow) {
            // Wenn alle Objekte die gefordert wurden, im Inventar sind
            var numberOfWinningObjectsInInventory = 0
            for (gameObject in winningRequiredGameObjectList) {
                if (action.inventory.isObjectInInventory(gameObject)) {
                    numberOfWinningObjectsInInventory++
                }
            }

            if (numberOfWinningObjectsInInventory == 3) {
                println("Spiel vorbei!!")
                exitProcess(0)
            } else {
                action.reset()
            }
        } else {
            action.talkedToCow = true
            action.reset()
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(650, 266), Player.Companion.Looking.RIGHT)
}
