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

    private val positions =
        arrayOf(
            Point(365F, 22F), // 1
            Point(490F, 18F), // 2
            Point(615F, 16F), // 3
            Point(735F, 16F), // 4
            Point(862F, 16F), // 5
        )

    private val positionsWide =
        arrayOf(
            Point(16F, 419F), // 1
            Point(110F, 419F), // 2
            Point(16F, 327F),  // 3
            Point(110F, 329F), // 4
            Point(62F, 243F), // 5
        )

    fun addGameObjectToInventory(gameObject: GameObject, isWidescreen: Boolean) {
        gameObject.isInInventory = true
        items.add(gameObject)
        gameObject.setPositionToPoint(getPositions(isWidescreen)[items.indexOf(gameObject)])
    }

    fun removeGameObject(gameObjectToBeRemoved: GameObject, isWidescreen: Boolean) {
        gameObjectToBeRemoved.isVisible = false
        gameObjectToBeRemoved.isInInventory = false
        items.remove(gameObjectToBeRemoved)

        // Position der übrigen Objekte anpassen
        rearrangeObjects(isWidescreen)
    }

    fun rearrangeObjects(isWidescreen: Boolean) {
        items.forEach { it.setPositionToPoint(getPositions(isWidescreen)[items.indexOf(it)]) }
    }

    private fun getPositions(isWidescreen: Boolean): Array<Point> =
        if (isWidescreen) {
            positionsWide
        } else {
            positions
        }

    fun isObjectInInventory(gameObject: GameObject): Boolean {
        return getObjectFromInventory(gameObject) != null
    }

    private fun getObjectFromInventory(gameObject: GameObject): GameObject? {
        return try {
            items.first { item -> item.name == gameObject.name }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    fun clearInventory() {
        items.forEach { item ->
            item.isVisible = false
            item.isInInventory = false
        }
        items.clear()
    }
}
