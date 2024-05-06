package org.wanne.model

import org.wanne.model.`object`.GameObject

class Action(var type: ActionType) {
    companion object {
        fun createDefaultAction(): Action {
            return Action(ActionType.NOTHING)
        }
    }

    var clickedObject: GameObject? = null

}
