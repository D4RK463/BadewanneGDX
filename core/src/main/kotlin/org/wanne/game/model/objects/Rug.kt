package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Rug(
    posX: Float = 278F,
    posY: Float = 190F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    var burned = false

    override fun getSprite(time: Float): Sprite =
        if (burned) {
            addPositionToSprite(itemAtlas.createSprite("TeppichBurned"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Teppich"))
        }

    override fun getName(): String = "Rug"

    override fun look(dialogBoard: DialogBoard) {
        if (burned) {
            dialogBoard.prepLookAt(choose(
                "Der Teppich ist so verkohlt, der fällt fast auseinander!",
                "The carpet is so charred, it's almost falling apart!"
            ))
        } else {
            dialogBoard.prepLookAt(choose(
                "Funkytastisch! Vielleicht lässt er sich bewegen.",
                "Funkytastic! Maybe it's moveable."
            ))
        }
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (!burned) {
            dialogBoard.prepUseIt(
                choose("Der Teppich lässt sich nicht bewegen!!", "The Carpet does not move!!"),
                null,
                action,
            )

            action.usedRug = true
        } else {
            this.isVisible = false
        }

        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            414,
            264
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = choose("Funky Teppich", "Funky carpet")
}
