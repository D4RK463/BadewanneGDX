package org.wanne.model.puzzle

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.model.AbstractObject
import org.wanne.model.Point

class Puzzle(
    posX: Float = 0F,
    posY: Float = 0F,
) : AbstractObject(posX, posY) {
    val puzzle: MutableList<PuzzlePiece> = ArrayList()

    private val positions =
        arrayOf(
            Point(357, 30), // 1
            Point(480, 30), // 2
            Point(611, 30), // 3
            Point(731, 30), // 4
            Point(857, 30), // 5
            Point(857, 30), // 6
            Point(857, 30), // 7
            Point(857, 30), // 8
            Point(857, 30), // 9
        )

    fun initializePuzzle(stage: Stage) {
        val randomNumberList: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled()
        randomNumberList.forEach { puzzle.add(PuzzlePiece(positions[it-1], pieceNumber = it)) }

        puzzle.forEach{ stage.addActor(it) }
    }

    override fun getSprite(): Sprite {
        TODO()
    }

    fun isPuzzleSolved(): Boolean {
        TODO("Not yet implemented")
    }
}
