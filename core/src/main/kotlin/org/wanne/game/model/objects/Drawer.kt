package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Drawer(
    posX: Float = 400F,
    posY: Float = 359F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Schrank"))

    override fun getName(): String = "Drawer"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Die Schubladen sind nur aufgemalt... lol.")
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            "Da lässt sich nix öffnen. Die sind nicht echt.",
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking?> = Pair(
        org.wanne.game.model.Point(
            510,
            308
        ), null)

    override fun getToolTipDescription(): String = "Holzschrank"
}
