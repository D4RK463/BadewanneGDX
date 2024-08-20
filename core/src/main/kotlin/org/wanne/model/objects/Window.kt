package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Window(
    posX: Float = 400F,
    posY: Float = 495F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Fenster"))

    override fun getName(): String = "Window"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Es ist halb offen.")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        when (action.lastSentence) {
            "Hinaus sehen!" -> {
                dialogBoard.prepUseIt(
                    "Ich seh den Eiswagen, neben einem Graffiti stehen.",
                    "Graffiti ansehn",
                    action,
                )
            }
            "Graffiti ansehn" -> {
                dialogBoard
                    .prepUseIt(
                        "Ohhh... es steht 'el Barto' drauf?!?",
                        null,
                        action,
                    ).also { action.reset() }
            }
            null -> {
                dialogBoard.prepUseIt(
                    "Ich pass net durch.",
                    "Hinaus sehen!",
                    action,
                )
            }
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(510, 308), null)

    override fun getToolTipDescription(): String = "Kleines Fenster"
}
