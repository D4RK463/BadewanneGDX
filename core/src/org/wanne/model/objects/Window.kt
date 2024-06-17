package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Window(posX: Float = 400F, posY: Float = 495F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Fenster"))

    override fun getName(): String = "Window"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Es ist halb offen.")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        when (action.lastSentence) {
            "Hinaus sehen!" -> {
                dialogBoard.prepUseIt(
                    "Ich seh den Eiswagen, neben einer Tonne stehn.",
                    "Tonne ansehn",
                    action,
                )
            }
            "Tonne ansehn" -> {
                dialogBoard
                    .prepUseIt(
                        "Ohhh... es steht 'Criban' drauf?!?",
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
}
