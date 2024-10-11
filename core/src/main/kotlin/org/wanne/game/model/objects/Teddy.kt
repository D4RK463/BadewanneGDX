package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player
import org.wanne.game.sound.collections.TeddySoundCollection

class Teddy(
    posX: Float = 855F,
    posY: Float = 307F,
    private val gameObjectToCheck: GameObject,
    game: WanneGame,
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    private val soundCollection = TeddySoundCollection(am)

    override fun getSprite(time: Float): Sprite =
        if (gameObjectToCheck is Mario && !gameObjectToCheck.poweredUp) {
            addPositionToSprite(itemAtlas.createSprite("TeddyTraurig"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Teddy"))
        }

    override fun getName(): String = "Teddy"

    override fun look(dialogBoard: DialogBoard) {
        if (gameObjectToCheck is Mario && !gameObjectToCheck.poweredUp) {
            dialogBoard.prepLookAt("Ein trauriger Teddy.", "So süß das man fast Karies davon kriegt.")
        } else {
            dialogBoard.prepLookAt("Ein Teddy.", "Er sieht schon etwas mutiger aus.")
        }
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (gameObjectToCheck is Mario && !gameObjectToCheck.poweredUp) {
            when (action.lastSentence) {
                "Wo bin ich hier?" -> {
                    dialogBoard.prepTalkTo(
                        "Teddy: ... *zitter*",
                        null,
                        null,
                        null,
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 0)
                    action.reset()
                }
                else -> {
                    dialogBoard.prepTalkTo(
                        "Hey Teddy!",
                        null,
                        "Wo bin ich hier?",
                        null,
                        action,
                    )
                }
            }
        } else {
            when (action.lastSentence) {
                "Was weißt du über den Wächter?" -> {
                    dialogBoard.prepTalkTo(
                        "Teddy: AAAHHHHHH!!!!",
                        null,
                        null,
                        null,
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 1)
                    action.reset()
                }
                "Was is los mit dir?" -> {
                    dialogBoard.prepTalkTo(
                        "Teddy: Hab Angst!",
                        null,
                        "Warum?",
                        "Was ist mit dem Arztbär los?",
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 2)
                }
                "Warum?" -> {
                    dialogBoard.prepTalkTo(
                        "Teddy: Hab Angst, dass ich den Wächter sehe.",
                        null,
                        null,
                        "Und was ist mit dem Arztbär los?",
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 3)
                }
                "Und was ist mit dem Arztbär los?", "Was ist mit dem Arztbär los?" -> {
                    dialogBoard.prepTalkTo(
                        "Teddy: Ach... Der hat zuviel von seiner ",
                        "eigenen Medizin genommen.",
                        null,
                        null,
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 4)
                    action.reset()
                }
                else -> {
                    dialogBoard.prepTalkTo(
                        "Kannste mir jetzt helfen?",
                        null,
                        "Was is los mit dir?",
                        "Was weißt du über den Wächter?",
                        action,
                    )
                }
            }
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            806,
            238
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Teddy"
}
