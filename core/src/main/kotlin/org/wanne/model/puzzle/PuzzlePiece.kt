package org.wanne.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.game.AssetsManager
import org.wanne.model.AbstractObject
import org.wanne.model.Point
import java.util.Random

class PuzzlePiece(
    point: Point,
    val pieceNumber: Int,
    var indexNumber: Int,
    am: AssetsManager
) : AbstractObject(point.x.toFloat(), point.y.toFloat(), am) {
    private val puzzleAtlas: TextureAtlas = am.get("pictures/Puzzle/puzzle.atlas")

    private val spriteRotationSpeed = 90

    private var puzzleSprite: Sprite

    private val random = Random()

    var currentRotation = 0

    init {
        x = posX
        y = posY

        puzzleSprite = addPositionToSprite(puzzleAtlas.createSprite(pieceNumber.toString()))
        randomizeRotation()

        height = getSprite(0F).height
        width = getSprite(0F).width
    }

    override fun getSprite(time: Float): Sprite = puzzleSprite

    fun rotate90() {
        var rotation: Float = puzzleSprite.rotation
        rotation -= spriteRotationSpeed
        currentRotation -= spriteRotationSpeed

        if (rotation == 360F || rotation == -360F) {
            rotation = 0F
            currentRotation = 0
        }
        puzzleSprite.setRotation(rotation)
    }

    fun hasCorrectRotation(): Boolean = currentRotation == 0

    private fun randomizeRotation() {
        val randomNumber = random.nextInt(3)
        for (i in 1..randomNumber) {
            rotate90()
        }
    }

    fun getPositionAsPoint(): Point = Point(posX.toInt(), posY.toInt())

    override fun setPositionToPoint(point: Point) {
        super.setPositionToPoint(point)
        puzzleSprite.x = posX
        puzzleSprite.y = posY
    }
}
