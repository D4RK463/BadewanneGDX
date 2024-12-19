package org.wanne.game.model

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import org.wanne.game.AssetsManager
import org.wanne.game.WanneGame

abstract class AbstractObject(
    var posX: Float,
    var posY: Float,
    val game: WanneGame
) : Actor() {
    val am: AssetsManager = game.am

    val itemAtlas: TextureAtlas = am.get("pictures/Items/items.atlas")

    var isInInventory = false

    abstract fun getSprite(time: Float): Sprite?

    open fun addPositionToSprite(sprite: Sprite): Sprite {
        sprite.x = if (!isInInventory) correctPositionX(posX) else posX
        sprite.y = if (!isInInventory) correctPositionY(posY) else posY
        return sprite
    }

    open fun dispose() {
    }

    open fun setPositionToPoint(point: Point) {
        posX = point.x
        posY = point.y
        x = posX
        y = posY
        positionChanged()
    }

    open fun draw(
        batch: Batch,
        parentAlpha: Float,
        time: Float
    ) {
        val sprite = getSprite(time)
        sprite?.setScale(scaleX, scaleY)
        sprite?.draw(batch, parentAlpha)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        this.draw(batch, parentAlpha, 0F)
    }

    override fun getWidth(): Float = getSprite(0F)?.width ?: 0F

    override fun getHeight(): Float = getSprite(0F)?.height ?: 0F

    override fun setScale(scaleX: Float, scaleY: Float) {
        this.scaleX = scaleX
        this.scaleY = scaleY
    }

    fun correctPositionX(currentX: Float) : Float {
        val extra = if (!game.classicMode()) {254} else {0}
        return currentX + extra
    }

    fun correctPositionY(currentY: Float) : Float {
        val extra = if (!game.classicMode()) {-50} else {0}
        return currentY + extra
    }
}
