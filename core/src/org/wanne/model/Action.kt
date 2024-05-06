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
        if (type == ActionType.LOOK_AT) {
            clickedObject?.look()
        }
    }
}
