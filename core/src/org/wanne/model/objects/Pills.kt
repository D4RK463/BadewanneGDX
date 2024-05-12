package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Pills(posX: Float = 187F, posY: Float = 388F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    override fun getSprite(): Sprite {
        return addPositionToSprite(itemAtlas.createSprite("Tabletten"))
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("Valium, hartes Zeug für'n Teddy.")
    }

    override fun use(dialogBoard: DialogBoard, action: Action) {
        dialogBoard.prepUseIt(
            "Gewinner nehmen keine Drogen!",
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(344, 264), Player.Companion.Looking.LEFT)
    }
}
