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

    var lastSentence: String? = null

    var usedRug = false

    fun reset(resetObjectToo : Boolean = true) {
        type = ActionType.NOTHING
        lastSentence = null

        if (resetObjectToo) {
            clickedObject = null
        }
    }

    fun action(dialogBoard: DialogBoard) {
        when (type) {
            ActionType.LOOK_AT -> clickedObject?.look(dialogBoard).also { this.reset() }
            ActionType.TALK_TO -> clickedObject?.talk(dialogBoard, this)
            ActionType.USE -> clickedObject?.use(dialogBoard, this)
            ActionType.COMBINE -> clickedObject?.combine(dialogBoard, this)
            ActionType.ADD_TO_INVENTORY -> clickedObject?.take(dialogBoard, this)
            ActionType.NOTHING -> return
        }
    }
}
