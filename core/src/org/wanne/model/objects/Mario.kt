package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.Action
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Mario(posX: Float = 82F, posY: Float = 345F) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    private var poweredUp = false

    private var talkForTheFirstTime = true

    override fun getSprite(): Sprite {
        return if (poweredUp) {
            addPositionToSprite(itemAtlas.createSprite("Feuermario"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Mario"))
        }
    }

    override fun look(dialogBoard: DialogBoard) {
        dialogBoard.prepLookAt("It's a him, Mario!")
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: Action,
    ) {
        if (!poweredUp) {

            if (talkForTheFirstTime) {

                when (action.lastSentence) {
                    "Was'n los ?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Die Prinzessin hat mich verlassen,",
                            "weil ich mein 'Feuer' verloren hab.",
                            "Erzähl mir mehr.",
                            "Mir doch egal",
                            action,
                        )
                    }
                    "Mir doch egal", "Erzähl mir mehr." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Seit dem der fiese Bowser weg ist,",
                            "ist die Action aus der Beziehung raus.",
                            null,
                            "*laber* ...",
                            action,
                        )
                    }
                    "*laber* ..." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Sie sagt ich bin ein 'Gefühlsstein'.",
                            "Dabei mag ich Steine nichtmal :(",
                            null,
                            "bla, bla, bla..." ,
                            action,
                        )
                    }
                    "bla, bla, bla..." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Weisst du vielleicht wie man ",
                            "das Feuer wieder entfachen kann?",
                            null,
                            null,
                            action,
                        )
                        talkForTheFirstTime = false
                        action.reset()
                    }
                    null -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Lass mich, ich bin gerad betrübt.",
                            null,
                            "Was'n los ?",
                            null,
                            action,
                        )
                    }
                }
            } else {
                dialogBoard.prepTalkTo(
                    "Mario: Weisst du vielleicht wie man ",
                    "das Feuer wieder entfachen kann?",
                    null,
                    null,
                    action,
                )
                action.reset()
            }
        }
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> {
        return Pair(Point(344, 264), Player.Companion.Looking.LEFT)
    }
}
