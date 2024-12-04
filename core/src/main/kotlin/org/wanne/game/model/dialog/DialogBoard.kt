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
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import org.wanne.game.VideoMode
import org.wanne.game.WanneGame
import org.wanne.game.dialog.Dialog
import org.wanne.game.model.AbstractObject
import org.wanne.game.model.ActionType
import org.wanne.game.model.PointAndClickAction

class DialogBoard(
    posX: Float = 0F,
    posY: Float = 137F,
    val skin: Skin,
    game: WanneGame
) : AbstractObject(posX, posY, game) {
    init {
        x = correctPositionX(posX.toInt()).toFloat()
        y = correctPositionY(posY.toInt()).toFloat()
        height = getSprite(0F)?.height ?: 0F
        width = getSprite(0F)?.width ?: 0F
    }

    private val label1: Label = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1", skin)
    private val label2: Label = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX2", skin)
    private val label3: Label = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX3", skin)
    private val label4: Label = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX4", skin)

    fun initialize(stage: Stage) {
        reset()

        label1.toFront()
        label1.x = 5F
        label1.y = 205F

        label2.toFront()
        label2.x = 5F
        label2.y = 185F

        initAnswerLabel(label3)
        label3.x = 5F
        label3.y = 165F

        initAnswerLabel(label4)
        label4.x = 5F
        label4.y = 145F

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

    override fun getSprite(time: Float): Sprite? = if (game.config.mode == VideoMode.CLASSIC.toString()) {
        addPositionToSprite(itemAtlas.createSprite("Brett"))
    } else {
        null
    }
}
