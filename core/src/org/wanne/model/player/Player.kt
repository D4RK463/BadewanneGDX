package org.wanne.model.player

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

        const val MOVE_PIXEL = 2
    }

    fun walkToPoint(
        move2posX: Int,
        move2posY: Int,
        lookingAtTheEnd: Looking?,
        action: () -> Unit,
    ) {
        val sprite = getSprite()

        // Punkt sollte in der Mitte der Figur sein
        val movePosX = move2posX - sprite.width / 2
        val movePosY = move2posY.toFloat() // - sprite.height / 2

        state = State.WALKING
        if (posX < movePosX) {
            changeView(Looking.RIGHT)
            posX += MOVE_PIXEL
            if (posY < movePosY) { // walks up-right
                posY += MOVE_PIXEL
            } else if (posY > movePosY) { // walks down-right
                posY -= MOVE_PIXEL
            }
        } else if (posX == movePosX) {
            if (posY < movePosY) { // walks up
                posY += MOVE_PIXEL
            } else if (posY == movePosY) { // stand

                // Ändert die Blickrichtung, so das auf das Objekt geschaut wird
                if (lookingAtTheEnd != null) {
                    changeView(lookingAtTheEnd)
                }
                state = State.STANDING

                // Führt die Action aus, wenn man angekommen ist
                action()
            } else { // if (posY > move2posY) // walks down
                posY -= MOVE_PIXEL
            }
        } else { // (posX > move2posX)
            changeView(Looking.LEFT)
            posX -= MOVE_PIXEL
            if (posY < movePosY) { // walks up-left
                posY += MOVE_PIXEL
            } else if (posY > movePosY) { // walks down-left
                posY -= MOVE_PIXEL
            }
        }
    }

    open fun changeView(newView: Looking) {
        this.looking = newView
    }

    /**
     * Stop! HammerTime!
     * Ändert den State auf STANDING
     */
    fun stopHammerTime() {
        state = State.STANDING
    }

    abstract fun getSpriteOfCurrentState(time: Float): Sprite

    abstract fun getSprite(): Sprite

    abstract fun dispose()
}
