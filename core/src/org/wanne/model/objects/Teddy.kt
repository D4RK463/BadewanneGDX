package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Teddy(posX: Float = 855F, posY: Float = 307F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    private var sad = true

    override fun getSprite(): Sprite =
        if (sad) {
            addPositionToSprite(itemAtlas.createSprite("TeddyTraurig"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Teddy"))
        }

    override fun getName(): String = "Teddy"

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("So süß das man fast Karies davon kriegt.")
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        if (sad) {
            when (action.lastSentence) {
                "Wo bin ich hier?" -> {
                    dialogBoard.prepTalkTo(
                        "Teddy: ... *zitter*",
                        null,
                        null,
                        null,
                        action,
                    )
                    action.reset()
                }
                null -> {
                    dialogBoard.prepTalkTo(
                        "Hey Teddy!",
                        null,
                        "Wo bin ich hier?",
                        null,
                        action,
                    )
                }
            }
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(806, 238), Player.Companion.Looking.RIGHT)
}
