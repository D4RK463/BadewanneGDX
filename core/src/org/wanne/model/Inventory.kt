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
            Point(357, 20), // 1
            Point(480, 20), // 2
            Point(611, 20), // 3
            Point(731, 20), // 4
            Point(857, 20), // 5
        )

    fun addGameObjectToInventory(gameObject: GameObject) {
        gameObject.setScale(0.75F)
        items.add(gameObject)
        gameObject.setPositionToPoint(positions[items.indexOf(gameObject)])
    }

    fun removeGameObject(gameObjectToBeRemoved: GameObject) {
        gameObjectToBeRemoved.isVisible = false
        items.remove(gameObjectToBeRemoved)

        // Position der übrigen Objekte anpassen
        items.forEach { it.setPositionToPoint(positions[items.indexOf(it)]) }
    }

    fun isObjectInInventory(gameObject: GameObject): Boolean = items.contains(gameObject)
}
