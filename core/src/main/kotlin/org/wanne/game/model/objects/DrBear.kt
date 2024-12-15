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
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
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
            dialogBoard.prepLookAt(
                game.choose("Doktor Bär, 'leicht' lädiert.", "Dr. Bear, 'slightly' damaged."),
                game.choose("Entschuldigung!", "Sorry!")
            )
        } else {
            dialogBoard.prepLookAt(
                game.choose("Doktor Bär.", "Dr. Bear."),
                game.choose("Ich hätte gern einen Termin für Sonntag :-)", "I'd like to make an appointment for Sunday :-)")
            )
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
            correctPositionX(344),
            correctPositionY(264)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Arztbär", "Dr. Bear")

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
        dialogBoard.prepLookAt(game.choose("Arztbär: Uhhhhhh!!", "Dr. Bear: Uhhhhhh!!"))
        game.soundManager.playSound(dialogCollection.soundCollection!!, 1)
        gameObjectToAppear.isVisible = true
        action.reset()
    }
}
