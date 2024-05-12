package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Door(posX: Float = 960F, posY: Float = 154F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Tuer"))
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Sie ist verschlossen!")
    }

    override fun use(dialogBoard: DialogBoard, action: Action) {
        dialogBoard.prepUseIt(
            "Ich kann sie nicht öffen. Sie ist fest verschlossen.",
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(862, 216), Player.Companion.Looking.RIGHT)
    }
}
