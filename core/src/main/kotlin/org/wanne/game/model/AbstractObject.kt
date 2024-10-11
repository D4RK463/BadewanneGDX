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

    abstract fun getSprite(time: Float): Sprite

    fun addPositionToSprite(sprite: Sprite): Sprite {
        sprite.x = posX
        sprite.y = posY
        return sprite
    }

    open fun dispose() {
    }

    open fun setPositionToPoint(point: Point) {
        posX = point.x.toFloat()
        posY = point.y.toFloat()
        x = posX
        y = posY
        positionChanged()
    }

    fun draw(
        batch: Batch?,
        parentAlpha: Float,
        time: Float
    ) {
        val sprite = getSprite(time)
        sprite.setScale(scaleX, scaleY)
        sprite.draw(batch, parentAlpha)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        val sprite = getSprite(0F)
        sprite.setScale(scaleX, scaleY)
        sprite.draw(batch, parentAlpha)
    }

    override fun getWidth(): Float = getSprite(0F).width

    override fun getHeight(): Float = getSprite(0F).height

    override fun setScale(scaleX: Float, scaleY: Float) {
        this.scaleX = scaleX
        this.scaleY = scaleY
    }
}
