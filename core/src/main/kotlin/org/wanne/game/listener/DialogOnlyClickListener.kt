package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.Statistic
import org.wanne.game.stage.DialogOnlyStage
import org.wanne.game.model.ActionType
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.network.SerializablePointAndClickAction

class DialogOnlyClickListener(
    val dialogBoard: DialogBoard
) : InputListener(), ExternalListener {
    override fun touchDown(
        event: InputEvent?,
        x: Float,
        y: Float,
        pointer: Int,
        button: Int,
    ): Boolean {
        Statistic.countClick()
        val stage = event?.stage as DialogOnlyStage

        stage.currentAction.type = ActionType.TALK_TO

//        println("${stage.currentAction.type} at $x:$y")

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage.hit(x, y, true)
        if (hitObject is Label) { // Im Dialog
            stage.currentAction.lastSentence = hitObject.text.toString()
            stage.currentAction.action(dialogBoard)

        }

        return true
    }

    override fun externalClick(
        stage: Stage,
        action: SerializablePointAndClickAction
    ): Boolean {
        TODO("Not yet implemented")
    }
}
