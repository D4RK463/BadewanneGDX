package org.wanne.model.player

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class Player(var posX: Float, var posY: Float, var looking: Looking) : Actor() {
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

        const val MOVE_PIXEL = 2
    }

    fun walkToPoint(
        move2posX: Int,
        move2posY: Int,
    ) {
        val sprite = getSprite()

        // Punkt sollte in der Mitte der Figur sein
        val movePosX = move2posX - sprite.width / 2
        val movePosY = move2posY // - sprite.height / 2

        state = State.WALKING
        if (posX < movePosX) {
            changeView(Looking.RIGHT)
            posX += MOVE_PIXEL
            if (posY < movePosY) { // walks up-right
                posY += MOVE_PIXEL
                // } else if (posY == move2posY) { // walks
                // right
            } else if (posY > movePosY) { // walks down-right
                posY -= MOVE_PIXEL
            }
        } else if (posX == movePosX.toFloat()) {
            if (posY < movePosY) { // walks up
                posY += MOVE_PIXEL
            } else if (posY == movePosY.toFloat()) { // stand
                state = State.STANDING
            } else { // if (posY > move2posY) // walks down
                posY -= MOVE_PIXEL
            }
        } else { // (posX > move2posX)
            changeView(Looking.LEFT)
            posX -= MOVE_PIXEL
            if (posY < movePosY) { // walks up-left
                posY += MOVE_PIXEL
                // } else if (posY == move2posY) { // walks
                // left
            } else if (posY > movePosY) { // walks down-left
                posY -= MOVE_PIXEL
            }
        }
    }

    open fun changeView(newView: Looking) {
        this.looking = newView
    }

    abstract fun getSpriteOfCurrentState(time: Float): Sprite

    abstract fun getSprite(): Sprite

    abstract fun dispose()
}
