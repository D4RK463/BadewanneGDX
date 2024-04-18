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
        println("$posX:$posY => $move2posX:$move2posY")

        val sprite = getSprite()

        val movePosX = move2posX - sprite.width / 2
        val movePosY = move2posY - sprite.height / 2

        println("fixed = $posX:$posY => $movePosX:$movePosY")

        state = State.WALKING
        if (posX < movePosX) {
            looking = Looking.RIGHT
            posX += movePixel
            if (posY < movePosY) { // walks up-right
                posY += movePixel
                // } else if (posY == move2posY) { // walks
                // right
            } else if (posY > movePosY) { // walks down-right
                posY -= movePixel
            }
        } else if (posX == movePosX.toFloat()) {
            if (posY < movePosY) { // walks up
                posY += movePixel
            } else if (posY == movePosY.toFloat()) { // stand
                state = State.STANDING
            } else { // if (posY > move2posY) // walks down
                posY -= movePixel
            }
        } else { // (posX > move2posX)
            looking = Looking.LEFT
            posX -= movePixel
            if (posY < movePosY) { // walks up-left
                posY += movePixel
                // } else if (posY == move2posY) { // walks
                // left
            } else if (posY > movePosY) { // walks down-left
                posY -= movePixel
            }
        }
    }

    abstract fun getSpriteOfCurrentState(stateTime: Float): Sprite

    abstract fun getSprite(): Sprite

    abstract fun dispose()
}
