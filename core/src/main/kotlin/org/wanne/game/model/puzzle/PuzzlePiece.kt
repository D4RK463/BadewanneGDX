package org.wanne.game.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.game.WanneGame
import org.wanne.game.model.AbstractObject
import org.wanne.game.model.Point
import java.util.*

class PuzzlePiece(
    point: Point,
    val pieceNumber: Int,
    var indexNumber: Int,
    game: WanneGame
) : AbstractObject(point.x, point.y, game) {
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

    fun getPositionAsPoint(): Point = Point(posX, posY)

    override fun setPositionToPoint(point: Point) {
        super.setPositionToPoint(point)
        puzzleSprite.x = posX
        puzzleSprite.y = posY
    }
}
