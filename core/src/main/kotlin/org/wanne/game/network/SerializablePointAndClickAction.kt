package org.wanne.game.network

import org.wanne.game.model.ActionType
import org.wanne.game.model.PointAndClickAction
import java.io.Serializable

class SerializablePointAndClickAction: Serializable {
    private constructor()

    var type: ActionType = ActionType.NOTHING
    var clickedObject: String? = null
    var combineObject1: String? = null
    var combineObject2: String? = null
    var lastSentence: String? = null

    // ToDo: Füllen irgendwo, irgendwie
    var x: Float = 0f
    var y: Float = 0f

    companion object {
        fun createFromPointAndClickAction(action: PointAndClickAction): SerializablePointAndClickAction {
            return SerializablePointAndClickAction().apply {
                type = action.type
                clickedObject = action.clickedObject?.name
                combineObject1 = action.combineObject1?.name
                combineObject2 = action.combineObject2?.name
                lastSentence = action.lastSentence
            }
        }
    }
}
