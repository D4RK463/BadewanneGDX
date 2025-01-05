package org.wanne.game.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Cursor
import org.wanne.game.Statistic.Companion.count
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.objects.GameObject

class PointAndClickAction(
    var type: ActionType,
) {
    companion object {
        fun createDefaultAction() = PointAndClickAction(ActionType.NOTHING)
    }

    var clickedObject: GameObject? = null

    var combineObject1: GameObject? = null
    private var combineObject2: GameObject? = null

    val inventory = Inventory.getInstance()

    var lastSentence: String = "Start"

    var usedRug = false
    var marioPoweredUp = false

    fun reset(resetObjectToo: Boolean = true) {
        count(type)
        type = ActionType.NOTHING
        lastSentence = "Start"

        if (resetObjectToo) {
            clickedObject = null
            combineObject1 = null
            combineObject2 = null
        }

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow)
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

    fun setCombineObject(combineObject: GameObject): Boolean =
        if (combineObject1 == null) {
            combineObject1 = combineObject
            false
        } else if (combineObject2 == null) {
            combineObject2 = combineObject
            true
        } else {
            true
        }

    fun getCombineObjectByType(className: String): GameObject? =
        if (combineObject1?.name == className) {
            combineObject1
        } else if (combineObject2?.name == className) {
            combineObject2
        } else {
            null
        }

    fun isCombineObject1InTheInventory(): Boolean =
        if (combineObject1 != null) {
            inventory.isObjectInInventory(combineObject1!!)
        } else {
            false
        }
}
