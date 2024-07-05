package org.wanne.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.AbstractObject

class PuzzlePiece(
    posX: Float,
    posY: Float,
    val pieceNumber: Int
): AbstractObject(posX, posY) {
    val puzzleAtlas: TextureAtlas = TextureAtlas("pictures/Puzzle/puzzle.atlas")

    override fun getSprite(): Sprite {
        return addPositionToSprite(puzzleAtlas.createSprite(pieceNumber.toString()))
    }

}