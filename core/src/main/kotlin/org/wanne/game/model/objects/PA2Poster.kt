package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class PA2Poster(
    posX: Float = 196F,
    posY: Float = 532F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("PA2"))

    override fun getName(): String = "PA2Poster"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(
            game.choose("Es ist von: Projekt Ananas 2.", "It's that movie: Project Pineapple 2."),
            game.choose("Der läuft doch aktuell im Kino oder?", "It's currently in the cinema, isn't it?")
        )
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(344),
            correctPositionY(264)
        ), Player.Companion.Looking.LEFT)

    override fun getToolTipDescription(): String = game.choose("Poster", "Poster")
}
