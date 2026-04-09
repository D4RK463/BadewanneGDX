package org.wanne.game

import org.wanne.game.model.ActionType

class Statistic {
    companion object {
        var clicks = 0L

        var useCount = 0L
        var lookCount = 0L
        var talkCount = 0L
        var combineCount = 0L
        var takeCount = 0L

        @JvmStatic
        fun count(type: ActionType) {
            when (type) {
                ActionType.USE -> useCount++
                ActionType.LOOK_AT -> lookCount++
                ActionType.TALK_TO -> talkCount++
                ActionType.COMBINE -> combineCount++
                ActionType.ADD_TO_INVENTORY -> takeCount++
                ActionType.NOTHING, ActionType.ROTATE, ActionType.EXCHANGE -> null
            }
            // println("$type u:$useCount l:$lookCount t:$talkCount c:$combineCount i:$takeCount")
        }

        @JvmStatic
        fun countClick() {
            clicks++
        }

        @JvmStatic
        fun reset() {
            useCount = 0L
            lookCount = 0L
            talkCount = 0L
            combineCount = 0L
            takeCount = 0L
            clicks = 0L
        }
    }
}
