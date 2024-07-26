package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.player.Player

class Cow(posX: Float = 1F,
          posY: Float = 1F,
          val game: WanneGame
) : GameObject(posX, posY) {
    init {
        x = posX
        y = posY
        height = getSprite().height
        width = getSprite().width
    }

    private var talkForTheFirstTime = true

    override fun getSprite(): Sprite = Sprite()

    override fun getName(): String = "Cow"

    override fun look(dialogBoard: DialogBoard) {
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking?> {
        return Pair(Point(posX.toInt(), posY.toInt()), Player.Companion.Looking.LEFT)
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (game.possessWinningObjects) {
            when (action.lastSentence) {
                "Alles klar..." -> {
                    game.cowIsBusy = true
                    game.screen = game.roomScreen
                }
                "Mach hin. Wir wolln Eis essen gehn!" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Ey jetzzt nicht frech werdenn.",
                        "Wehe ihr saagt jemand daas ich euch raus lasse!",
                        null,
                        "Alles klar...",
                        action,
                    )
                }
                "Wir haben dein ganzes Zeug gesammelt." -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Sehr guuuut. Daann mach ich euch",
                        "gleich die Tüür auf.",
                        null,
                        "Mach hin. Wir wolln Eis essen gehn!",
                        action,
                    )
                }
                else -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Wawas willst duu schon wieder?",
                        null,
                        null,
                        "Wir haben dein ganzes Zeug gesammelt.",
                        action,
                    )
                }
            }
        } else {
            when (action.lastSentence) {
                "Hi...Ente hier, lass", "uns verdammt nochma hier raus!!!" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Dadas kann ich nicht,",
                        "auauch ich muss mich an didie Regeln halten.",
                        "Von welchen Regeln sprichst du?",
                        "Warum hast du uns entführt?",
                        action,
                    )
                }
                "Von welchen Regeln sprichst du?" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Ichch soll euch 15 Jajahre gefangen haltenn.",
                        "Warum dadarf ich nicht sasagen!",
                        null,
                        "Gibt es nicht irgendeine andere Möglichkeit?",
                        action,
                    )
                }
                "Warum hast du uns entführt?" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Ichch hole jejeden der im Abfluss",
                        "landet und brringe ihn ins Zzimmer.",
                        null,
                        "Wie bitte?",
                        action,
                    )
                }
                "Wie bitte?" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Ichch werde dafühür bezahhlt! Frage",
                        "dich lieber warum dudu im Abfluss gelandet bist.",
                        "Was können wir tun um hier rauszukommen?",
                        "Wer bezahlt dich?",
                        action,
                    )
                }
                "Wer bezahlt dich?" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Wewen ich dir das ssage",
                        "mümüsste ich dich tötöten.",
                        null,
                        "Was können wir tun um hier rauszukommen?",
                        action,
                    )
                }
                "Was können wir tun um hier rauszukommen?", "Gibt es nicht irgendeine andere Möglichkeit?" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Dudu hast Glück, ich bibin sehr gut gelaunt.",
                        null,
                        null,
                        "Und?",
                        action,
                    )
                }
                "Und?", "Was brauch ich noch ma?" -> {
                    dialogBoard.prepTalkTo(
                        "Kuh: Bebesorge mir Valium, memeine",
                        "Kuhglocke und den Milchabsauger 2000. 'Klick'",
                        null,
                        "Na schön, wenn's sein muss.",
                        action,
                    )
                }
                "Na schön, wenn's sein muss." -> {
                    talkForTheFirstTime = false
                    game.screen = game.roomScreen
                }
                else -> {
                    if (talkForTheFirstTime) {
                        dialogBoard.prepTalkTo(
                            "Kuh: Wäwächter hier!!",
                            null,
                            "Hi...Ente hier, lass",
                            "uns verdammt nochma hier raus!!!",
                            action,
                        )
                    } else {
                        dialogBoard.prepTalkTo(
                            "Kuh: Wäwächter hier!!",
                            null,
                            null,
                            "Was brauch ich noch ma?",
                            action,
                        )
                    }
                }
            }
        }
    }
}