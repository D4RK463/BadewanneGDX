package org.wanne.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.model.AbstractObject

class VictoryMessage(
    posX: Float = 0F,
    posY: Float = 0F,
) : AbstractObject(posX, posY) {
    private val puzzleAtlas: TextureAtlas = TextureAtlas("pictures/Puzzle/puzzle.atlas")

    override fun getSprite(time: Float): Sprite = addPositionToSprite(puzzleAtlas.createSprite("PuzzleSieg"))

    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

}
