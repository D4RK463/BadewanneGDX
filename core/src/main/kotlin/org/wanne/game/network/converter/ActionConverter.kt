package org.wanne.game.network.converter

import org.wanne.game.WanneGame
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.objects.GameObject
import org.wanne.game.network.model.ActionWrapper
import org.wanne.game.network.model.SerializableAction

class ActionConverter(val game: WanneGame) {

    fun convertToActionWrapper(action: SerializableAction): ActionWrapper {

        val pointAndClickAction = PointAndClickAction(type = action.type).apply {
            clickedObject = action.clickedObject?.let { findGameObjectByName(it) }
            combineObject1 = action.combineObject1?.let { findGameObjectByName(it) }
            combineObject2 = action.combineObject2?.let { findGameObjectByName(it) }
            lastSentence = action.lastSentence ?: "Start"
            external = true
        }
        return ActionWrapper().apply {
            this.pointAndClickAction = pointAndClickAction
            this.x = action.x
            this.y = action.y
        }
    }

    private fun findGameObjectByName(name: String): GameObject? {
        println("Searching for GameObject with name: $name")

        game.items.asList().forEach { item ->
            if (item.name == name) {
                return item
            }
        }
        return null
    }
}
