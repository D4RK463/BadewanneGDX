package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.stage.DialogOnlyStage
import org.wanne.model.ActionType
import org.wanne.model.dialog.DialogBoard

class DialogOnlyClickListener(
    val dialogBoard: DialogBoard
) : InputListener() {
    override fun touchDown(
        event: InputEvent?,
        x: Float,
        y: Float,
        pointer: Int,
        button: Int,
    ): Boolean {
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
}