package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Drawer(
    posX: Float = 400F,
    posY: Float = 359F,
    am: AssetsManager
) : GameObject(posX, posY, am) {
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

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> = Pair(Point(510, 308), null)

    override fun getToolTipDescription(): String = "Holzschrank"
}
