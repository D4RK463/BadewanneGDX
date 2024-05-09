package org.wanne.model

import org.wanne.model.dialog.DialogBoard
import org.wanne.model.objects.GameObject

class Action(var type: ActionType) {

    companion object {
        fun createDefaultAction(): Action {
            return Action(ActionType.NOTHING)
        }
    }

    var clickedObject: GameObject? = null

    fun reset() {
        type = ActionType.NOTHING
        clickedObject = null
    }

    fun action(dialogBoard: DialogBoard) {
        when (type) {
            ActionType.LOOK_AT -> clickedObject?.look(dialogBoard)
            ActionType.TALK_TO -> clickedObject?.talk(dialogBoard)
            ActionType.USE -> clickedObject?.use(dialogBoard)
            ActionType.COMBINE -> clickedObject?.combine(dialogBoard)
            ActionType.ADD_TO_INVENTORY -> clickedObject?.take(dialogBoard)
            ActionType.NOTHING -> return
        }
    }
}
