package org.wanne.model

import org.wanne.model.objects.GameObject

class Inventory private constructor() {
    companion object {
        private var instance: Inventory? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Inventory().also { instance = it }
            }
    }

    private val items = mutableSetOf<GameObject>()

    private val positions =
        arrayOf(
            Point(357, 30), // 1
            Point(480, 30), // 2
            Point(611, 30), // 3
            Point(731, 30), // 4
            Point(857, 30), // 5
        )

    fun addGameObjectToInventory(gameObject: GameObject) {
        items.add(gameObject)
        gameObject.setPositionToPoint(positions[items.indexOf(gameObject)])
    }

    fun removeGameObject(gameObjectToBeRemoved: GameObject) {
        gameObjectToBeRemoved.isVisible = false
        items.remove(gameObjectToBeRemoved)
        println("removed ${gameObjectToBeRemoved.name}")

        // Position der übrigen Objekte anpassen
        items.forEach { it.setPositionToPoint(positions[items.indexOf(it)]) }
    }

    fun isObjectInInventory(gameObject: GameObject): Boolean = items.contains(gameObject)
}
