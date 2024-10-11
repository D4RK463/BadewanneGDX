package org.wanne.game.model.animation

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.wanne.game.AssetsManager

abstract class Animation(val posX: Float, val posY: Float, var visible: Boolean, val am: AssetsManager) {

    private var elapsedTime: Float = 0F

    abstract fun getTextureRegionOfCurrentState(time: Float): TextureRegion?

    fun draw(batch: Batch, time: Float) {
        if (visible) {
            elapsedTime += time

            getTextureRegionOfCurrentState(elapsedTime)?.run {
                batch.draw(this, posX, posY)
            }
        }
    }
}
