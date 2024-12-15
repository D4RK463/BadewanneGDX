package org.wanne.game.model.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.WanneGame
import org.wanne.game.dialog.collections.MarioDialogCollection
import org.wanne.game.model.ActionType
import org.wanne.game.model.Point
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.animation.FireAnimation
import org.wanne.game.model.animation.PowerUpAnimation
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.objects.MarioState.AFTER_RUG_USAGE
import org.wanne.game.model.objects.MarioState.NORMAL
import org.wanne.game.model.objects.MarioState.NORMAL_END
import org.wanne.game.model.objects.MarioState.PISSED
import org.wanne.game.model.objects.MarioState.POWERED_UP
import org.wanne.game.model.objects.MarioState.POWERED_UP_END
import org.wanne.game.model.player.Player

class Mario(
    posX: Float = 82F,
    posY: Float = 345F,
    private val gameObjectToManipulate: GameObject,
    private val gameObjectToAppear: GameObject,
    private val fireAnimation: FireAnimation,
    private val powerUpAnimation: PowerUpAnimation,
    game: WanneGame,
) : GameObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    private var state = NORMAL

    private val dialogCollection = MarioDialogCollection(am)

    fun poweredUp(): Boolean {
        return state in arrayOf(
            POWERED_UP,
            POWERED_UP_END,
            AFTER_RUG_USAGE,
            PISSED
        )
    }

    fun didntTalkForTheFirstTime(): Boolean {
        return state == NORMAL_END
    }

    override fun getSprite(time: Float): Sprite =
        if (poweredUp()) {
            addPositionToSprite(itemAtlas.createSprite("Feuermario"))
        } else {
            addPositionToSprite(itemAtlas.createSprite("Mario"))
        }

    override fun getName(): String = "Mario"

    override fun look(dialogBoard: DialogBoard) {
        when (state) {
            POWERED_UP, POWERED_UP_END, AFTER_RUG_USAGE -> {
                dialogBoard.prepLookAt("It's a him, Feuermario!")
            }

            PISSED -> {
                dialogBoard.prepLookAt("It's a him, pissed off Feuermario!")
            }

            else -> {
                dialogBoard.prepLookAt("It's a him, Mario!")
            }
        }
    }

    override fun talk(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        val dialog = game.dialogManager.getFurtherDialogAndPlaySound(
            setStartSentenceAccordingToState(action.lastSentence, action),
            dialogCollection
        )
        dialogBoard.prepTalkTo(dialog, action)

        if (!game.talkedToCow) {
            when (state) {
                NORMAL -> {
                    if (dialog?.isStateChanged() == true) {
                        state = NORMAL_END
                        action.reset()
                    }
                }

                NORMAL_END -> {
                    action.reset()
                }

                POWERED_UP -> {
                    if (dialog?.isStateChanged() == true) {
                        state = POWERED_UP_END

                        // Telefonnummer-Zettel ins Inventar packen
                        if (!gameObjectToAppear.isVisible) {
                            gameObjectToAppear.isVisible = true
                            action.inventory.addGameObjectToInventory(gameObjectToAppear)
                        }

                        action.reset()
                    }
                }

                POWERED_UP_END -> {
                }

                else -> {
                    throw IllegalStateException("Unmöglichen Dialog-Status erreicht")
                }
            }
        } else {
            if (!action.usedRug) {
                action.reset()
            } else {
                when (state) {
                    POWERED_UP_END -> {
                        state = AFTER_RUG_USAGE
                    }

                    AFTER_RUG_USAGE -> {
                        if (dialog?.isStateChanged() == true) {
                            state = PISSED

                            if (gameObjectToManipulate is Rug) {
                                gameObjectToManipulate.burned = true
                                fireAnimation.visible = true
                            }

                            action.reset()
                        }
                    }

                    PISSED -> {
                        action.reset()
                    }

                    else -> {
                        throw IllegalStateException("Unmöglichen Dialog-Status erreicht")
                    }
                }
            }
        }
    }

    private fun setStartSentenceAccordingToState(sentence: String, action: PointAndClickAction): String {
        if (sentence == "Start") {
            if (!game.talkedToCow) {
                if (state == NORMAL) {
                    return "Start1"
                }
                if (state == NORMAL_END) {
                    return "Start2"
                }
                if (state == POWERED_UP) {
                    return "Start3"
                }
                if (state == POWERED_UP_END) {
                    return "Start4"
                }
            } else {
                if (!action.usedRug) {
                    return "Start5"
                } else {
                    if (state == POWERED_UP_END || state == AFTER_RUG_USAGE) {
                        return "Start7"
                    }
                    if (state == PISSED) {
                        return "Start6"
                    }
                }
            }
        }

        return sentence
    }

    override fun combine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        if (state == NORMAL_END) {
            doCombine(dialogBoard, action, this, "FireFlower")
        } else {
            super.combine(dialogBoard, action)
        }
    }

    override fun afterCombine(
        dialogBoard: DialogBoard,
        action: PointAndClickAction,
    ) {
        state = POWERED_UP

        powerUpAnimation.visible = true
        game.soundManager.playSound(dialogCollection.soundCollection!!, 22)
        action.marioPoweredUp = true
        action.type = ActionType.TALK_TO
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)
    }

    override fun getInteractPosition(): Pair<Point, Player.Companion.Looking> = Pair(
        Point(
            correctPositionX(344),
            correctPositionY(264)
        ), Player.Companion.Looking.LEFT
    )

    override fun getToolTipDescription(): String = game.choose("Mario", "Mario")
}

enum class MarioState {
    NORMAL,
    NORMAL_END,
    POWERED_UP,
    POWERED_UP_END,
    AFTER_RUG_USAGE,
    PISSED
}
