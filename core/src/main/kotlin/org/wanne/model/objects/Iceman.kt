package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.model.Point
import org.wanne.model.animation.IcemanAnimation
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Iceman(
    posX: Float = 82F,
    posY: Float = 345F,
    private val icemanAnimation: IcemanAnimation,
    private val game: WanneGame,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width

        icemanAnimation.visible = true
    }

    override fun getSprite(): Sprite = addPositionToSprite(itemAtlas.createSprite("Eismann"))

    override fun getName(): String = "Iceman"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Chilliger Dude!")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Funky Eismann!"

}
