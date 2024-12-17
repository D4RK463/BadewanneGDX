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
import org.wanne.game.model.Point
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
    private lateinit var shadowLabel1: Label
    private lateinit var label2: Label
    private lateinit var shadowLabel2: Label
    private lateinit var label3: Label
    private lateinit var shadowLabel3: Label
    private lateinit var label4: Label
    private lateinit var shadowLabel4: Label

    fun initialize(stage: Stage) {
        val label1Pair = createDialogLabelAndShadow(game.choose(Point(5F, 205F), Point(15F, 135F)))
        label1 = label1Pair.first
        shadowLabel1 = label1Pair.second

        val label2Pair = createDialogLabelAndShadow(game.choose(Point(5F, 185F), Point(15F, 95F)))
        label2 = label2Pair.first
        shadowLabel2 = label2Pair.second

        val label3Pair = createDialogLabelAndShadow(game.choose(Point(5F, 165F), Point(15F, 55F)), Color.ORANGE)
        label3 = label3Pair.first
        shadowLabel3 = label3Pair.second
        initAnswerLabel(label3)

        val label4Pair = createDialogLabelAndShadow(game.choose(Point(5F, 145F), Point(15F, 15F)), Color.ORANGE)
        label4 = label4Pair.first
        shadowLabel4 = label4Pair.second
        initAnswerLabel(label4)

        reset()

        stage.addActor(shadowLabel1)
        stage.addActor(shadowLabel2)
        stage.addActor(shadowLabel3)
        stage.addActor(shadowLabel4)
        stage.addActor(label1)
        stage.addActor(label2)
        stage.addActor(label3)
        stage.addActor(label4)
    }

    private fun createDialogLabelAndShadow(position: Point, color: Color? = null): Pair<Label, Label> {
        val label = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", game.currentSkin())
        label.toFront()
        color?.let { label.color = it }
        label.x = position.x
        label.y = position.y

        val shadowLabel = Label("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", game.currentSkin())
        shadowLabel.toFront()
        shadowLabel.color = Color.BLACK
        shadowLabel.x = position.x + 2
        shadowLabel.y = position.y + 2

        return Pair(label, shadowLabel)
    }

    private fun initAnswerLabel(label: Label) {
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
        shadowLabel1.isVisible = false
        resetAllExceptNo1()
    }

    private fun resetAllExceptNo1() {
        label2.isVisible = false
        shadowLabel2.isVisible = false
        label3.isVisible = false
        shadowLabel3.isVisible = false
        label4.isVisible = false
        shadowLabel4.isVisible = false
    }

    fun prepLookAt(
        lookAtSentence: String,
        lookAtSentence2: String? = null,
    ) {
        setText(label1, shadowLabel1, lookAtSentence)
        label2.isVisible = false

        if (lookAtSentence2 != null) {
            setText(label2, shadowLabel2, lookAtSentence2)
        }

        label3.isVisible = false
        shadowLabel3.isVisible = false
        label4.isVisible = false
        shadowLabel4.isVisible = false

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

    private fun prepTalkTo(
        talkToSentence: String,
        talkToSentence2: String?,
        answer1: String?,
        answer2: String?,
        action: PointAndClickAction,
    ) {
        setText(label1, shadowLabel1, talkToSentence)
        resetAllExceptNo1()

        if (talkToSentence2 != null) {
            setText(label2, shadowLabel2, talkToSentence2)
        }

        if (answer1 != null) {
            setText(label3, shadowLabel3, answer1)
        }

        if (answer2 != null) {
            setText(label4, shadowLabel4, answer2)
        }

        action.type = ActionType.TALK_TO
        this.isVisible = true
    }

    fun prepUseIt(
        explainSentence: String,
        furtherDo: String?,
        action: PointAndClickAction,
    ) {
        setText(label1, shadowLabel1, explainSentence)
        resetAllExceptNo1()

        if (furtherDo != null) {
            setText(label3, shadowLabel3, furtherDo)
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
        shadowLabel1.remove()
        label2.remove()
        shadowLabel2.remove()
        label3.remove()
        shadowLabel3.remove()
        label4.remove()
        shadowLabel4.remove()
        return super.remove()
    }

    private fun setText(label: Label, shadowLabel: Label, text: String) {
        label.setText(text)
        label.isVisible = true
        if (!game.classicMode()) {
            shadowLabel.setText(text)
            shadowLabel.isVisible = true
        }
    }

}
