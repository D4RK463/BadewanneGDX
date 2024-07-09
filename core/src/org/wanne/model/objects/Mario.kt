package org.wanne.model.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.model.PointAndClickAction
import org.wanne.model.ActionType
import org.wanne.model.Point
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Mario(
    posX: Float = 82F,
    posY: Float = 345F,
    private val gameObjectToManipulate: GameObject,
    private val gameObjectToAppear: GameObject,
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    var poweredUp = false

    var talkForTheFirstTime = true

    private var pissed = false

    private var talkForTheFirstTimeAfterPoweredUp = true

    override fun getSprite(): Sprite =
        if (poweredUp) {
            addPositionToSprite(itemAtlas.createSprite("Feuermario"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Mario"))
        }

    override fun getName(): String = "Mario"

    override fun look(dialogBoard: DialogBoard) {
        if (poweredUp) {
            if (pissed) {
                dialogBoard.prepLookAt("It's a him, pissed off Feuermario!")
            } else {
                dialogBoard.prepLookAt("It's a him, Feuermario!")
            }
        } else {
            dialogBoard.prepLookAt("It's a him, Mario!")
        }
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (!poweredUp) {
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
                        "bla, bla, bla...",
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
                else -> {
                    if (talkForTheFirstTime) {
                        dialogBoard.prepTalkTo(
                            "Mario: Lass mich, ich bin gerad betrübt.",
                            null,
                            "Was'n los ?",
                            null,
                            action,
                        )
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
        } else {
            if (!action.talkedToCow) {
                when (action.lastSentence) {
                    "Wie sind wir hier her gekommen?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Hier landen alle Leute die ",
                            "sich im Ausguss verirren.",
                            "Warum ist die Tür verschlossen?",
                            "Und was mach ich nun?",
                            action,
                        )
                    }
                    "Warum ist die Tür verschlossen?", "Geht net, die Tür ist zu." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Der Wächter hat sie versiegelt.",
                            null,
                            "Wo ist der Wächter?",
                            "Wer ist der Wächter?",
                            action,
                        )
                    }
                    "Und was mach ich nun?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Vielleicht solltest du versuchen ",
                            "aus dem Raum zu entkommen?",
                            null,
                            "Geht net, die Tür ist zu.",
                            action,
                        )
                    }
                    "Wo ist der Wächter?", "Wo kann ich ihn finden?", "Wo ist nochma der Wächter?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Ich hab keine Ahnung wo er ist.",
                            "Aber ich hab seine Telefonnummer.",
                            null,
                            null,
                            action,
                        )

                        talkForTheFirstTimeAfterPoweredUp = false

                        // Telefonnummer-Zettel ins Inventar packen
                        if (!gameObjectToAppear.isVisible) {
                            gameObjectToAppear.isVisible = true
                            action.inventory.addGameObjectToInventory(gameObjectToAppear)
                        }

                        action.reset()
                    }
                    "Wer ist der Wächter?", "Wer ist nochma der Wächter?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Das ist eine abgrundtief böse Kreatur.",
                            "Es gibt niemand der sie gesehen hat und noch lebt.",
                            "Klar doch!",
                            "Weiter...",
                            action,
                        )
                    }
                    "Weiter..." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Es wird gemunkelt das der Wächter die ",
                            "Seelen derer erntet die sich im Ausguss verirren.",
                            "Übertrieben!",
                            "WOW!!!",
                            action,
                        )
                    }
                    "WOW!!!" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Er soll riesig groß sein mit ",
                            "fürchterlichen Klauen und Eiter triefendem Maul.",
                            "Wo kann ich ihn finden?",
                            "Du laberst doch nur!",
                            action,
                        )
                    }
                    "Du laberst doch nur!", "Übertrieben!", "Klar doch!" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Wenn du meinst aber ich hab dich gewarnt.",
                            null,
                            null,
                            "Wo kann ich ihn finden?",
                            action,
                        )
                    }
                    else -> {
                        if (talkForTheFirstTimeAfterPoweredUp) {
                            dialogBoard.prepTalkTo(
                                "Mario: Klasse, Danke. Damit kann ich ",
                                "die Prinzessin bestimmt wieder zurückgewinnen.",
                                "Wie sind wir hier her gekommen?",
                                "Warum ist die Tür verschlossen?",
                                action,
                            )
                        } else {
                            dialogBoard.prepTalkTo(
                                "Ich hab da was nicht mitbekommen.",
                                null,
                                "Wer ist nochma der Wächter?",
                                "Wo ist nochma der Wächter?",
                                action,
                            )
                        }
                    }
                }
            } else {
                if (!action.usedRug) {
                    dialogBoard.prepTalkTo(
                        "Mario: Du kannst froh sein das man durchs Telefon ",
                        "nix riecht!",
                        null,
                        null,
                        action,
                    )
                    action.reset()
                } else {
                    when (action.lastSentence) {
                        "Warum liegt hier eigentlich Stroh?" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Warum hast du ne Maske auf?",
                                null,
                                null,
                                null,
                                action,
                            )
                            action.reset()
                        }
                        "Warum lässt sich der Teppich nicht bewegen?" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Langsam hab ich keine Lust mehr dir ",
                                "zu helfen. Wir sind schon quitt.",
                                null,
                                "Bitte, bitte...",
                                action,
                            )
                        }
                        "Was riecht hier so komisch?" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Frag ma deinen dicken Freund da drüben. XD",
                                null,
                                "Warum liegt hier eigentlich Stroh?",
                                "Warum lässt sich der Teppich nicht bewegen?",
                                action,
                            )
                        }
                        "Bitte, bitte..." -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Es wird erzählt das der Wächter ",
                                "den Teppich festgenagelt hat.",
                                null,
                                "Kannst du mir helfen den Teppich loszuwerden?",
                                action,
                            )
                        }
                        "Kannst du mir helfen den Teppich loszuwerden?" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Ich würde dir ja helfen aber ich will nicht.",
                                null,
                                "Du #*%&!!! Ich hasse dich!!",
                                "*schnief* Bitte, bitte ich tu auch alles für dich!",
                                action,
                            )
                        }
                        "Du #*%&!!! Ich hasse dich!!", "*schnief* Bitte, bitte ich tu auch alles für dich!" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Nagut, nagut, aber wehe du erzählst es ",
                                "den anderen. Mehr werde ich nicht helfen!",
                                null,
                                null,
                                action,
                            )

                            if (gameObjectToManipulate is Rug) {
                                gameObjectToManipulate.burned = true
                            }
                            pissed = true

                            action.reset()
                        }
                        else -> {
                            if (pissed) {
                                dialogBoard.prepTalkTo(
                                    "Mario: Das reicht jetzt! Wir kennen uns nicht!",
                                    null,
                                    null,
                                    null,
                                    action,
                                )
                            } else {
                                dialogBoard.prepTalkTo(
                                    "Hi Mario!!",
                                    null,
                                    "Warum lässt sich der Teppich nicht bewegen?",
                                    "Was riecht hier so komisch?",
                                    action,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (!talkForTheFirstTime) {
            doCombine(dialogBoard, action, this, "FireFlower")
        } else {
            super.combine(dialogBoard, action)
        }
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        poweredUp = true
        action.marioPoweredUp = true
        action.type = ActionType.TALK_TO
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)
}
