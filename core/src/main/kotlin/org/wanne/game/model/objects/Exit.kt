package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Exit(
    posX: Float = 968F,
    posY: Float = 220F,
    am: AssetsManager,
    private val game: WanneGame,
) : org.wanne.game.model.objects.GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Ausgang"))

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

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            900,
            216
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Süße Freiheit!"
}
