package org.wanne.game.model.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import org.wanne.game.AssetsManager

abstract class Player(
    var posX: Float,
    var posY: Float,
    var looking: Looking,
    var scaleX: Float,
    var scaleY: Float,
    val am: AssetsManager
) {
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
        // Sprite Skalierung ausrechnen
        val screenHeight = Gdx.graphics.height
        val scalePercent = (posY / screenHeight) / 150

        // Bewegung rechnen
        // Endpunkt sollte in der Mitte der Figur sein
        val sprite = getSprite()
        val movePosX = move2posX - sprite.width / 2
        val movePosY = move2posY.toFloat() // - sprite.height / 2

        state = State.WALKING
        if (posX < movePosX) {
            changeView(Looking.RIGHT)
            posX += MOVE_PIXEL
            if (posY < movePosY) { // walks up-right
                posY += MOVE_PIXEL
                scaleDown(scalePercent)
            } else if (posY > movePosY) { // walks down-right
                posY -= MOVE_PIXEL
                scaleUp(scalePercent)
            }
        } else if (posX == movePosX) {
            if (posY < movePosY) { // walks up
                posY += MOVE_PIXEL
                scaleDown(scalePercent)
            } else if (posY == movePosY) { // stand

                // Ändert die Blickrichtung, so das auf das Objekt geschaut wird
                lookingAtTheEnd?.let { changeView(newView = it) }
                state = State.STANDING

                // Führt die Action aus, wenn man angekommen ist
                action()
            } else { // if (posY > move2posY) // walks down
                posY -= MOVE_PIXEL
                scaleUp(scalePercent)
            }
        } else { // (posX > move2posX)
            changeView(Looking.LEFT)
            posX -= MOVE_PIXEL
            if (posY < movePosY) { // walks up-left
                posY += MOVE_PIXEL
                scaleDown(scalePercent)
            } else if (posY > movePosY) { // walks down-left
                posY -= MOVE_PIXEL
                scaleUp(scalePercent)
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

    fun scaleUp(scalePercent: Float) {
        scaleX += (scalePercent * scaleX)
        scaleY += (scalePercent * scaleY)
    }

    fun scaleDown(scalePercent: Float) {
        scaleX -= (scalePercent * scaleX)
        scaleY -= (scalePercent * scaleY)
    }

    abstract fun getSpriteOfCurrentState(time: Float): Sprite

    abstract fun getSprite(): Sprite

    abstract fun dispose()
}
