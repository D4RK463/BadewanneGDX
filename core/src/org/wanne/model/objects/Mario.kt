package org.wanne.model.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.sound.collections.MarioSoundCollection
import org.wanne.model.ActionType
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.animation.FireAnimation
import org.wanne.model.animation.PowerUpAnimation
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Mario(
    posX: Float = 82F,
    posY: Float = 345F,
    private val gameObjectToManipulate: GameObject,
    private val gameObjectToAppear: GameObject,
    private val fireAnimation: FireAnimation,
    private val powerUpAnimation: PowerUpAnimation,
    private val game: WanneGame,
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

    private val soundCollection = MarioSoundCollection()

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
                    game.soundManager.playSound(soundCollection, 1)
                }
                "Mir doch egal", "Erzähl mir mehr." -> {
                    dialogBoard.prepTalkTo(
                        "Mario: Seit dem der fiese Bowser weg ist,",
                        "ist die Action aus der Beziehung raus.",
                        null,
                        "*laber* ...",
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 2)
                }
                "*laber* ..." -> {
                    dialogBoard.prepTalkTo(
                        "Mario: Sie sagt ich bin ein 'Gefühlsstein'.",
                        "Dabei mag ich Steine nichtmal :(",
                        null,
                        "bla, bla, bla...",
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 3)
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
                    game.soundManager.playSound(soundCollection, 4)
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
                        game.soundManager.playSound(soundCollection, 0)
                    } else {
                        dialogBoard.prepTalkTo(
                            "Mario: Weisst du vielleicht wie man ",
                            "das Feuer wieder entfachen kann?",
                            null,
                            null,
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 4)
                        action.reset()
                    }
                }
            }
        } else {
            if (!game.talkedToCow) {
                when (action.lastSentence) {
                    "Wie sind wir hier her gekommen?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Hier landen alle Leute die ",
                            "sich im Ausguss verirren.",
                            "Warum ist die Tür verschlossen?",
                            "Und was mach ich nun?",
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 6)
                    }
                    "Warum ist die Tür verschlossen?", "Geht net, die Tür ist zu." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Der Wächter hat sie versiegelt.",
                            null,
                            "Wo ist der Wächter?",
                            "Wer ist der Wächter?",
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 7)
                    }
                    "Und was mach ich nun?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Vielleicht solltest du versuchen ",
                            "aus dem Raum zu entkommen?",
                            null,
                            "Geht net, die Tür ist zu.",
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 8)
                    }
                    "Wo ist der Wächter?", "Wo kann ich ihn finden?", "Wo ist nochma der Wächter?" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Ich hab keine Ahnung wo er ist.",
                            "Aber ich hab seine Telefonnummer.",
                            null,
                            null,
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 9)

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
                        game.soundManager.playSound(soundCollection, 10)
                    }
                    "Weiter..." -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Es wird gemunkelt das der Wächter die ",
                            "Seelen derer erntet die sich im Ausguss verirren.",
                            "Übertrieben!",
                            "WOW!!!",
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 11)
                    }
                    "WOW!!!" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Er soll riesig groß sein mit ",
                            "fürchterlichen Klauen und Eiter triefendem Maul.",
                            "Wo kann ich ihn finden?",
                            "Du laberst doch nur!",
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 12)
                    }
                    "Du laberst doch nur!", "Übertrieben!", "Klar doch!" -> {
                        dialogBoard.prepTalkTo(
                            "Mario: Wenn du meinst aber ich hab dich gewarnt.",
                            null,
                            null,
                            "Wo kann ich ihn finden?",
                            action,
                        )
                        game.soundManager.playSound(soundCollection, 13)
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
                            game.soundManager.playSound(soundCollection, 5)
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
                        "Mario: Ich kann die Angst in deinen Augen sehen. Sei ",
                        "bloß froh das man durchs Telefon nichts riechen kann!",
                        null,
                        null,
                        action,
                    )
                    game.soundManager.playSound(soundCollection, 21)
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
                            game.soundManager.playSound(soundCollection, 15)
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
                            game.soundManager.playSound(soundCollection, 16)
                        }
                        "Was riecht hier so komisch?" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Frag ma deinen dicken Freund da drüben. XD",
                                null,
                                "Warum liegt hier eigentlich Stroh?",
                                "Warum lässt sich der Teppich nicht bewegen?",
                                action,
                            )
                            game.soundManager.playSound(soundCollection, 17)
                        }
                        "Bitte, bitte..." -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Na gut, es wird erzählt das der Wächter ",
                                "den Teppich festgenagelt hat.",
                                null,
                                "Kannst du mir helfen den Teppich loszuwerden?",
                                action,
                            )
                            game.soundManager.playSound(soundCollection, 18)
                        }
                        "Kannst du mir helfen den Teppich loszuwerden?" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Ich würde dir ja helfen aber ich will nicht.",
                                null,
                                "Du #*%&!!! Ich hasse dich!!",
                                "*schnief* Bitte, bitte ich tu auch alles für dich!",
                                action,
                            )
                            game.soundManager.playSound(soundCollection, 19)
                        }
                        "Du #*%&!!! Ich hasse dich!!", "*schnief* Bitte, bitte ich tu auch alles für dich!" -> {
                            dialogBoard.prepTalkTo(
                                "Mario: Na gut, na gut, aber wehe du erzählst es ",
                                "den Anderen. Mehr werde ich nicht helfen!",
                                null,
                                null,
                                action,
                            )
                            game.soundManager.playSound(soundCollection, 20)

                            if (gameObjectToManipulate is Rug) {
                                gameObjectToManipulate.burned = true
                                fireAnimation.visible = true
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
                                game.soundManager.playSound(soundCollection, 14)
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
        powerUpAnimation.visible = true
        game.soundManager.playSound(soundCollection, 22)
        action.marioPoweredUp = true
        action.type = ActionType.TALK_TO
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(Point(344, 264), Player.Companion.Looking.LEFT)
}
