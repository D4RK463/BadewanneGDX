package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Exit(
    posX: Float = 968F,
    posY: Float = 220F,
    game: WanneGame,
) : GameObject(posX, posY, game) {
    init {
        initialize()
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Ausgang"))

    override fun getName(): String = "Exit"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(game.choose("Da gehts nach draußen!", "That's the way out!"))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        game.screen = game.outsideScreen
    }

    override fun take(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        game.screen = game.outsideScreen
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(
            game.choose("Wie soll ich denn den Ausgang mit was kombinieren?", "How am I supposed to combine something with the exit?"),
            game.choose("Probiers mal mit 'Benutzen' :) ", "Try 'use' :)")
        )
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(
            game.choose("Wollen wir uns wirklich mit dem Ausgang unterhalten?", "You really want me to talk to the exit?"),
            game.choose("Ich würde vorschlagen, wir gehn einfach..", "I suggest, we just leave.."),
        )
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(900F),
            correctPositionY(216F)
        ), Player.Companion.Looking.RIGHT
    )

    override fun getToolTipDescription(): String = game.choose("Süße Freiheit!", "Sweet freedom!")
}
