package org.wanne.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.AbstractObject
import org.wanne.model.Point
import java.util.Random

class PuzzlePiece(
    point: Point,
    val pieceNumber: Int,
) : AbstractObject(point.x.toFloat(), point.y.toFloat()) {
    private val puzzleAtlas: TextureAtlas = TextureAtlas("pictures/Puzzle/puzzle.atlas")

    private val spriteRotationSpeed = 90

    private var puzzleSprite: Sprite

    private val random = Random()

    init {
        puzzleSprite = addPositionToSprite(puzzleAtlas.createSprite(pieceNumber.toString()))
        randomizeRotation()
    }

    override fun getSprite(): Sprite = puzzleSprite

    fun rotate90() {
        var rotation: Float = puzzleSprite.rotation
        rotation += spriteRotationSpeed
        puzzleSprite.setRotation(rotation)
    }

    fun hasCorrectRotation(): Boolean = puzzleSprite.rotation == 0F

    fun randomizeRotation() {
        val randomNumber = random.nextInt(3)
        for (i in 1..randomNumber) {
            rotate90()
        }
    }
}
