package org.wanne.model

import com.badlogic.gdx.graphics.g2d.Sprite

abstract class Player(var posX: Float, var posY: Float, var looking: Looking) {
    var state: State = State.STANDING

    companion object {
        enum class Looking {
            RIGHT,
            LEFT,
        }

        enum class State {
            STANDING,
            WALKING,
        }

        val movePixel = 2
    }

    fun walkToPoint(
        move2posX: Int,
        move2posY: Int,
    ) {
        println("move from: $posX:$posY => $move2posX:$move2posY")

        state = State.WALKING
        if (posX < move2posX) {
            looking = Looking.RIGHT
            posX += movePixel
            if (posY < move2posY) { // walks up-right
                posY += movePixel
                // } else if (posY == move2posY) { // walks
                // right
            } else if (posY > move2posY) { // walks down-right
                posY -= movePixel
            }
        } else if (posX == move2posX.toFloat()) {
            if (posY < move2posY) { // walks up
                posY += movePixel
            } else if (posY == move2posY.toFloat()) { // stand
                state = State.STANDING
            } else { // if (posY > move2posY) // walks down
                posY -= movePixel
            }
        } else { // (posX > move2posX)
            looking = Looking.LEFT
            posX -= movePixel
            if (posY < move2posY) { // walks up-left
                posY += movePixel
                // } else if (posY == move2posY) { // walks
                // left
            } else if (posY > move2posY) { // walks down-left
                posY -= movePixel
            }
        }
    }

    abstract fun getSpriteOfCurrentState(stateTime: Float): Sprite

    abstract fun dispose()
}
