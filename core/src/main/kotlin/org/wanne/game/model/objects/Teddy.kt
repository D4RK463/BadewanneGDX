package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.dialog.collections.TeddyDialogCollection
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Teddy(
    posX: Float = 855F,
    posY: Float = 307F,
    private val gameObjectToCheck: GameObject,
    game: WanneGame,
) : GameObject(posX, posY, game) {
    init {
        initialize()
    }

    private val dialogCollection = TeddyDialogCollection(am)

    override fun getSprite(time: Float): Sprite =
        if (gameObjectToCheck is Mario && !gameObjectToCheck.poweredUp()) {
            addPositionToSprite(itemAtlas.createSprite("TeddyTraurig"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Teddy"))
        }

    override fun getName(): String = "Teddy"

    override fun look(dialogBoard: DialogBoard) {
        if (gameObjectToCheck is Mario && !gameObjectToCheck.poweredUp()) {
            dialogBoard.prepLookAt(
                game.choose("Ein trauriger Teddy.", "One sad teddy."),
                game.choose("So süss das man fast Karies davon kriegt.", "So cute that it almost gives you tooth decay.")
            )
        } else {
            dialogBoard.prepLookAt(
                game.choose("Ein Teddy.", "A teddy."),
                game.choose("Sie sieht schon etwas mutiger aus.", "She looks much braver now.")
            )
        }
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        val dialog = game.dialogManager.getFurtherDialogAndPlaySound(
            setStartSentenceAccordingToState(action.lastSentence),
            dialogCollection
        )
        dialogBoard.prepTalkTo(dialog, action)

        if (dialog?.isStateChanged() == true) {
            action.reset()
        }
    }

    private fun setStartSentenceAccordingToState(sentence: String): String {
        if (sentence == "Start") {
            return if (gameObjectToCheck is Mario && !gameObjectToCheck.poweredUp()) {
                "Start1"
            } else {
                "Start2"
            }
        }

        return sentence
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(806F),
            correctPositionY(238F)
        ), Player.Companion.Looking.RIGHT
    )

    override fun getToolTipDescription(): String = game.choose("Teddy", "Teddy")
}
