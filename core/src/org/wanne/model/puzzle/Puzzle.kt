package org.wanne.model.puzzle

import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.model.Point

class Puzzle() {
    private val puzzle: MutableList<PuzzlePiece> = ArrayList()

    private val positions =
        arrayOf(
            Point(210, 510), // 1
            Point(410, 510), // 2
            Point(610, 510), // 3
            Point(210, 310), // 4
            Point(410, 310), // 5
            Point(610, 310), // 6
            Point(210, 110), // 7
            Point(410, 110), // 8
            Point(610, 110), // 9
        )

    fun initializePuzzle(stage: Stage) {
        val randomNumberList: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled()
        randomNumberList.forEachIndexed { index, element -> puzzle.add(PuzzlePiece(
            point = positions[index],
            pieceNumber = element,
            indexNumber = index
        )) }

        puzzle.forEach { println("${it.pieceNumber}|${it.indexNumber} -> ${it.posX}:${it.posY} R${it.currentRotation}") }

        puzzle.forEach{ stage.addActor(it) }
    }

    fun exchangePieces(action: PuzzleAction) {
        println("${action.changePiece1?.pieceNumber} mit ${action.changePiece2?.pieceNumber}")

        if (action.changePiece1?.pieceNumber != action.changePiece2?.pieceNumber) {
            val indexPiece1 = action.changePiece1!!.indexNumber
            val indexPiece2 = action.changePiece2!!.indexNumber

            val piece1 = puzzle[indexPiece1]
            val piece2 = puzzle[indexPiece2]

            piece1.indexNumber = indexPiece2
            piece1.setPositionToPoint(positions[indexPiece2])
            piece2.indexNumber = indexPiece1
            piece2.setPositionToPoint(positions[indexPiece1])

            puzzle[indexPiece1] = piece2
            puzzle[indexPiece2] = piece1
        }

        action.reset()
    }

    fun isPuzzleSolved(): Boolean {
        TODO("Not yet implemented")
    }
}
