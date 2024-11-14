package org.wanne.game.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.player.Player

class Door(
    posX: Float = 960F,
    posY: Float = 154F,
    game: WanneGame
) : GameObject(posX, posY, game) {
    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = addPositionToSprite(itemAtlas.createSprite("Tuer"))

    override fun getName(): String = "Door"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt(choose(
            "Eine verschlossene Tür. Aber warum ist die so groß?",
            "A closed door. But why is it so large?"
        ))
    }

    override fun use(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        dialogBoard.prepUseIt(
            choose("Ich kann sie nicht öffnen. Sie ist fest verschlossen.", "I can't open it. It's locked."),
            null,
            action,
        )
        action.reset()
    }

    override fun getInteractPosition(): Pair<org.wanne.game.model.Point, Player.Companion.Looking> = Pair(
        org.wanne.game.model.Point(
            862,
            216
        ), Player.Companion.Looking.RIGHT)

    override fun getToolTipDescription(): String = choose("Tür", "Door")
}
