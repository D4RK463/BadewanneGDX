package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.dialog.Dialog
import org.wanne.game.dialog.collections.DrBearDialogCollection
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class DrBear(
    posX: Float = 187F,
    posY: Float = 388F,
    private val gameObjectToAppear: GameObject,
    game: WanneGame,
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    private val dialogCollection = DrBearDialogCollection(am)

    private var broken = false

    override fun getSprite(time: Float): Sprite =
        if (broken) {
            addPositionToSprite(itemAtlas.createSprite("ArztbaerOffen"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Arztbaer"))
        }

    override fun getName(): String = "DrBear"

    override fun look(dialogBoard: DialogBoard) {
        if (broken) {
            dialogBoard.prepLookAt("Doktor Bär, 'leicht' lädiert.", "Entschuldigung!")
        } else {
            dialogBoard.prepLookAt("Doktor Bär.", "Ich hätte gern einen Termin für Sonntag :-)")
        }
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {

        val dialog: Dialog?

        if (broken) {
            dialog = game.dialogManager.getFurtherDialogAndPlaySound(
                "Start1",
                dialogCollection
            )
            dialogBoard.prepTalkTo(dialog, action)
        } else {
            dialog = game.dialogManager.getFurtherDialogAndPlaySound(
                "Start2",
                dialogCollection
            )
            dialogBoard.prepTalkTo(dialog, action)
        }
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            344,
            264
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = "Arztbär"

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
        game.soundManager.playSound(dialogCollection.soundCollection, 1)
        gameObjectToAppear.isVisible = true
        action.reset()
    }
}
