package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.dialog.collections.WindowDialogCollection
import org.wanne.game.model.ActionType
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Window(
    posX: Float = 400F,
    posY: Float = 495F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Fenster"))

    override fun getName(): String = "Window"

    private val dialogCollection = WindowDialogCollection(am)

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose("Es ist halb offen.", "It's half open."))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        var sentence = action.lastSentence
        if (sentence == "Start") {
            sentence = "Start1"
        }

        val dialog = game.dialogManager.getFurtherDialogAndPlaySound(
            sentence,
            dialogCollection
        )
        dialogBoard.prepTalkTo(dialog, action)
        action.type = ActionType.USE // a little cheat

        if (dialog?.isStateChanged() == true) {
            action.reset()
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(
        Point(
            510,
            308
        ), null)

    override fun getToolTipDescription(): String = choose("Kleines Fenster", "Little window")
}
