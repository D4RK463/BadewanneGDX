package org.wanne.model

class Action(var type: ActionType) {
    companion object {
        fun createDefaultAction(): Action {
            return Action(ActionType.NOTHING)
        }
    }
}
