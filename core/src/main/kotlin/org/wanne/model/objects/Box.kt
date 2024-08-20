package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Box(
    posX: Float = 678F,
    posY: Float = 272F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Kiste"))

    override fun getName(): String = "Box"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Eine blaue Kiste, nix besonderes.")
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(638, 268), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = "Blaue Kiste"
}
