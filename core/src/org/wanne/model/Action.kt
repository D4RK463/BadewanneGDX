package org.wanne.model

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

    fun action() {
        when (type) {
            ActionType.LOOK_AT -> clickedObject?.look()
            ActionType.TALK_TO -> clickedObject?.talk()
            ActionType.USE -> clickedObject?.use()
            ActionType.COMBINE -> clickedObject?.combine()
            ActionType.ADD_TO_INVENTORY -> clickedObject?.take()
            ActionType.NOTHING -> return
        }
    }
}
