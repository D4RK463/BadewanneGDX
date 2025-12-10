package org.wanne.game.network.model

import org.wanne.game.model.ActionType
import org.wanne.game.model.PointAndClickAction
import java.io.Serializable

class SerializableAction: Serializable {
    private constructor()

    var type: ActionType = ActionType.NOTHING
    var clickedObject: String? = null
    var combineObject1: String? = null
    var combineObject2: String? = null
    var lastSentence: String? = null

    var puzzleSolved = false
    var talkedToCow = false
    var possessWinningObjects = false
    var cowIsBusy = false
    var arrivedOutside = false

    var x: Float = 0f
    var y: Float = 0f

    companion object {
        fun createFromPointAndClickAction(
            action: PointAndClickAction,
            xPos: Float,
            yPos: Float,
            puzzleSolvedState: Boolean = false,
            talkedToCowState: Boolean = false,
            possessWinningObjectsState: Boolean = false,
            cowIsBusyState: Boolean = false,
            arrivedOutsideState: Boolean = false
        ): SerializableAction {
            return SerializableAction().apply {
                type = action.type
                clickedObject = action.clickedObject?.name
                combineObject1 = action.combineObject1?.name
                combineObject2 = action.combineObject2?.name
                lastSentence = action.lastSentence
                x = xPos
                y = yPos

                // ToDo: Setzen for real
                puzzleSolved = puzzleSolvedState
                talkedToCow = talkedToCowState
                possessWinningObjects = possessWinningObjectsState
                cowIsBusy = cowIsBusyState
                arrivedOutside = arrivedOutsideState
            }
        }
    }

    override fun toString(): String {
        return "SerializableAction(type=$type, clickedObject=$clickedObject, combineObject1=$combineObject1, combineObject2=$combineObject2, lastSentence=$lastSentence, puzzleSolved=$puzzleSolved, talkedToCow=$talkedToCow, possessWinningObjects=$possessWinningObjects, cowIsBusy=$cowIsBusy, arrivedOutside=$arrivedOutside, x=$x, y=$y)"
    }


}
