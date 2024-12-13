package org.wanne.game.model

import org.wanne.game.model.objects.GameObject

class Inventory private constructor() {
    companion object {
        private var instance: Inventory? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Inventory().also { instance = it }
            }
    }

    val items = mutableSetOf<GameObject>()

    // ToDo Positionen anpassen
    private val positions =
        arrayOf(
            Point(365, 22), // 1
            Point(490, 18), // 2
            Point(615, 16), // 3
            Point(735, 16), // 4
            Point(862, 16), // 5
        )

    // ToDo: Neue Positionen für alle Objekte anpassen
    fun addGameObjectToInventory(gameObject: GameObject) {
        gameObject.isInInventory = true
        items.add(gameObject)
        gameObject.setPositionToPoint(positions[items.indexOf(gameObject)])
    }

    fun removeGameObject(gameObjectToBeRemoved: GameObject) {
        gameObjectToBeRemoved.isVisible = false
        gameObjectToBeRemoved.isInInventory = false
        items.remove(gameObjectToBeRemoved)

        // Position der übrigen Objekte anpassen
        items.forEach { it.setPositionToPoint(positions[items.indexOf(it)]) }
    }

    fun isObjectInInventory(gameObject: GameObject): Boolean = items.contains(gameObject)
}
