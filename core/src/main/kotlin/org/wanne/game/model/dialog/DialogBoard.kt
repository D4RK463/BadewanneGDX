package org.wanne.game.model.dialog

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.WanneGame
import org.wanne.game.dialog.Dialog
import org.wanne.game.model.AbstractObject
import org.wanne.game.model.ActionType
import org.wanne.game.model.PointAndClickAction

class DialogBoard(
    posX: Float = 0F,
    posY: Float = 137F,
    game: WanneGame
) : AbstractObject(posX, posY, game) {
    init {
        x = correctPositionX(posX)
        y = correctPositionY(posX)
        height = getSprite(0F)?.height ?: 0F
        width = getSprite(0F)?.width ?: 0F
    }

    private lateinit var label1: Label
    private lateinit var label2: Label
    private lateinit var label3: Label
    private lateinit var label4: Label

    fun initialize(stage: Stage) {
        label1 = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1", game.currentSkin())
        label1.toFront()
        label1.color = Color.WHITE
        label1.x = 5F
        label1.y = if (game.classicMode()) {
            205F
        } else {
            135F
        }

        label2 = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX2", game.currentSkin())
        label2.toFront()
        label2.color = Color.WHITE
        label2.x = 5F
        label2.y = if (game.classicMode()) {
            185F
        } else {
            95F
        }

        label3 = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX3", game.currentSkin())
        initAnswerLabel(label3)
        label3.x = 5F
        label3.color = Color.ORANGE
        label3.y = if (game.classicMode()) {
            165F
        } else {
            55F
        }

        label4 = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX4", game.currentSkin())
        initAnswerLabel(label4)
        label4.x = 5F
        label4.color = Color.ORANGE
        label4.y = if (game.classicMode()) {
            145F
        } else {
            15F
        }

        reset()

        stage.addActor(label1)
        stage.addActor(label2)
        stage.addActor(label3)
        stage.addActor(label4)
    }

    private fun initAnswerLabel(label: Label) {
        label.toFront()
        label.color = Color.ORANGE
        label.addListener(
            object : InputListener() {
                override fun enter(
                    event: InputEvent?,
                    x: Float,
                    y: Float,
                    pointer: Int,
                    fromActor: Actor?,
                ) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand)
                }

                override fun exit(
                    event: InputEvent?,
                    x: Float,
                    y: Float,
                    pointer: Int,
                    toActor: Actor?,
                ) {
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)
                }
            },
        )
    }

    fun reset() {
        this.isVisible = false
        label1.isVisible = false
        label2.isVisible = false
        label3.isVisible = false
        label4.isVisible = false
    }

    fun prepLookAt(
        lookAtSentence: String,
        lookAtSentence2: String? = null,
    ) {
        label1.setText(lookAtSentence)
        label1.isVisible = true
        label2.isVisible = false

        if (lookAtSentence2 != null) {
            label2.setText(lookAtSentence2)
            label2.isVisible = true
        }

        label3.isVisible = false
        label4.isVisible = false

        this.isVisible = true
    }

    fun prepTalkTo(dialog: Dialog?, action: PointAndClickAction) {
        dialog?.let {
            if (it.isFilled()) {
                prepTalkTo(
                    it.talkToSentence,
                    it.talkToSentence2,
                    it.answer1,
                    it.answer2,
                    action
                )
            }
        }
    }

    fun prepTalkTo(
        talkToSentence: String,
        talkToSentence2: String?,
        answer1: String?,
        answer2: String?,
        action: PointAndClickAction,
    ) {
        label1.setText(talkToSentence)
        label1.isVisible = true
        label2.isVisible = false
        label3.isVisible = false
        label4.isVisible = false

        if (talkToSentence2 != null) {
            label2.setText(talkToSentence2)
            label2.isVisible = true
        }

        if (answer1 != null) {
            label3.setText(answer1)
            label3.isVisible = true
        }

        if (answer2 != null) {
            label4.setText(answer2)
            label4.isVisible = true
        }

        action.type = ActionType.TALK_TO
        this.isVisible = true
    }

    fun prepUseIt(
        explainSentence: String,
        furtherDo: String?,
        action: PointAndClickAction,
    ) {
        label1.setText(explainSentence)
        label1.isVisible = true
        label2.isVisible = false
        label3.isVisible = false
        label4.isVisible = false

        if (furtherDo != null) {
            label3.setText(furtherDo)
            label3.isVisible = true
        }

        action.type = ActionType.USE
        this.isVisible = true
    }

    override fun getSprite(time: Float): Sprite? = if (game.classicMode()) {
        addPositionToSprite(itemAtlas.createSprite("Brett"))
    } else {
        null
    }

    override fun remove(): Boolean {
        label1.remove()
        label2.remove()
        label3.remove()
        label4.remove()
        return super.remove()
    }

}
