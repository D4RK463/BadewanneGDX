package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Sprite

abstract class Player(var posX: Float, var posY: Float, var looking: Looking) {
    var state: State = State.STANDING

    abstract fun getSpriteOfCurrentState(stateTime: Float): Sprite

    abstract fun dispose()

    companion object {
        enum class Looking {
            RIGHT,
            LEFT,
        }

        enum class State {
            STANDING,
            WALKING,
        }
    }
}
