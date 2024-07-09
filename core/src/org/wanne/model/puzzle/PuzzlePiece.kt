package org.wanne.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.AbstractObject
import java.util.Random

class PuzzlePiece(
    posX: Float,
    posY: Float,
    val pieceNumber: Int
): AbstractObject(posX, posY) {
    val puzzleAtlas: TextureAtlas = TextureAtlas("pictures/Puzzle/puzzle.atlas")

    private val spriteRotationSpeed = 90

    private var puzzleSprite: Sprite

    private val random = Random()

    init {
        puzzleSprite = addPositionToSprite(puzzleAtlas.createSprite(pieceNumber.toString()))
    }

    override fun getSprite(): Sprite {
        return puzzleSprite
    }

    fun rotate90() {
        var rotation: Float = puzzleSprite.rotation
        rotation += spriteRotationSpeed
        puzzleSprite.setRotation(rotation)
    }

    fun hasCorrectRotation(): Boolean {
        return puzzleSprite.rotation == 0F
    }

    fun randomizeRotation() {
        val randomNumber = random.nextInt(3)
        for(i in 1..randomNumber) {
            rotate90()
        }
    }
}