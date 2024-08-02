package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Telephone(
    posX: Float = 745F,
    posY: Float = 366F,
    val winningRequiredGameObjectList: List<GameObject>,
    private val game: WanneGame,
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
        if (game.talkedToCow) {
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
        // Wenn wir schon zum 2ten Mal anrufen
        if (game.talkedToCow) {
            // Wenn alle Objekte die gefordert wurden, im Inventar sind
            var numberOfWinningObjectsInInventory = 0
            for (gameObject in winningRequiredGameObjectList) {
                if (action.inventory.isObjectInInventory(gameObject)) {
                    numberOfWinningObjectsInInventory++
                }
            }

            if (numberOfWinningObjectsInInventory == 3) {
                // Wenn die Kuh die Tür schon aufgemacht hat, ist besetzt
                if (game.cowIsBusy) {
                    dialogBoard.prepLookAt("Piep, Piep, Piep...", "Scheint besetzt zu sein.")
                } else {
                    game.possessWinningObjects = true
                    game.screen = game.cowPhoneScreen
                }
            } else {
                game.screen = game.cowPhoneScreen
            }

            action.reset()
        } else {
            // Das erste Mal mit der Kuh reden
            game.screen = game.cowPhoneScreen

            game.talkedToCow = true
            action.reset()
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(650, 266), Player.Companion.Looking.RIGHT)

    override fun getInventoryScale(): Float = 0.70F
}
