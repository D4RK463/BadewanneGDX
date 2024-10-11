package org.wanne.game.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import org.wanne.game.WanneGame
import org.wanne.game.model.AbstractObject

class VictoryMessage(
    posX: Float = 0F,
    posY: Float = 0F,
    game: WanneGame
) : AbstractObject(posX, posY, game) {
    private val puzzleAtlas: TextureAtlas = am.get("pictures/Puzzle/puzzle.atlas")

    override fun getSprite(time: Float): Sprite = addPositionToSprite(puzzleAtlas.createSprite("PuzzleSieg"))

    init {
        x = posX
        y = posY
        height = getSprite(0F).height
        width = getSprite(0F).width
    }

}
