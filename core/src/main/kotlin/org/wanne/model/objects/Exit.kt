package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Exit(
    posX: Float = 968F,
    posY: Float = 220F,
    private val game: WanneGame,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Ausgang"))

    override fun getName(): String = "Exit"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Da gehts nach draußen!")
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
            "Wie soll ich denn den Ausgang mit was kombinieren?",
            "Probiers mal mit 'Benutzen' :) ",
        )
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepLookAt(
            "Wollen wir uns wirklich mit dem Ausgang unterhalten?",
            "Ich würde vorschlagen, wir gehn einfach..",
        )
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(900, 216), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Süße Freiheit!"
}
