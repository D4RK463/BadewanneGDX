package org.wanne.model

import com.badlogic.gdx.graphics.Texture

class Player(val textureRight: Texture, val textureLeft: Texture, var posX: Float, var posY: Float, var looking: Looking) {
    fun getTextureOfCurrentState(): Texture {
        return if (looking == Looking.RIGHT) {
            textureLeft
        } else {
            textureRight
        }
    }

    companion object {
        enum class Looking {
            RIGHT,
            LEFT,
        }
    }
}
