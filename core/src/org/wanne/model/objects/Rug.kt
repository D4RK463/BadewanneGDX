package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Rug(
    posX: Float = 278F,
    posY: Float = 190F
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    var burned = false

    override fun getSprite(): Sprite {
        // ToDo: Asset für den verbrannten Teppich erstellen
        return addPositionToSprite(itemAtlas.createSprite("Teppich"))
    }

    override fun getName(): String = "Rug"

    override fun look(dialogBoard: DialogBoard) {
        if (burned) {
            dialogBoard.prepLookAt("Der Teppich ist so verkohlt, der fällt fast auseinander!")
        } else {
            dialogBoard.prepLookAt("Funkytastisch! Vielleicht lässt er sich bewegen.")
        }
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: Action,
    ) {

        if (!burned) {
            dialogBoard.prepUseIt(
                "Teppich lässt sich nicht bewegen!!",
                null,
                action,
            )

            action.usedRug = true
        } else {
            this.isVisible = false
        }

        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(414, 264), Player.Companion.Looking.LEFT)
}
