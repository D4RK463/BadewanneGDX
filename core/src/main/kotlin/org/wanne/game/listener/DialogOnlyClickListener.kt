package org.wanne.game.listener

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import org.wanne.game.Statistic
import org.wanne.game.model.ActionType
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.network.NetworkManager
import org.wanne.game.network.model.ActionWrapper
import org.wanne.game.network.model.SerializableAction
import org.wanne.game.stage.DialogOnlyStage

class DialogOnlyClickListener(
    val dialogBoard: DialogBoard,
    private val networkManager: NetworkManager?
) : InputListener(), ExternalListener {
    override fun touchDown(
        event: InputEvent?,
        x: Float,
        y: Float,
        pointer: Int,
        button: Int,
    ): Boolean {
        val stage = event?.stage as DialogOnlyStage
        networkManager?.sendClick(SerializableAction.createFromPointAndClickAction(stage.currentAction, x, y))
        return internalClick(stage, stage.currentAction, x, y)
    }

    override fun externalClick(
        stage: Stage,
        action: ActionWrapper,
    ): Boolean {
        return internalClick(stage as DialogOnlyStage, action.pointAndClickAction!!, action.x, action.y)
    }

    private fun internalClick(
        stage: DialogOnlyStage,
        action: PointAndClickAction,
        x: Float,
        y: Float,
    ): Boolean {
        Statistic.countClick()
        action.type = ActionType.TALK_TO

//        println("${action.type} at $x:$y")

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage.hit(x, y, true)
        if (hitObject is Label) { // Im Dialog
            action.lastSentence = hitObject.text.toString()
            action.action(dialogBoard)
        }

        return true
    }
}
