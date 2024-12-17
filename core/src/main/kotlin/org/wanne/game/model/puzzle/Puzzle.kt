package org.wanne.game.model.puzzle

import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.game.WanneGame
import org.wanne.game.model.Point

class Puzzle (private val game: WanneGame) {
    private val puzzle: MutableList<PuzzlePiece> = ArrayList()

    private val positions =
        arrayOf(
            game.choose(Point(210F, 510F), Point(360F, 460F)), // 1
            game.choose(Point(410F, 510F), Point(560F, 460F)), // 2
            game.choose(Point(610F, 510F), Point(760F, 460F)), // 3
            game.choose(Point(210F, 310F), Point(360F, 260F)), // 4
            game.choose(Point(410F, 310F), Point(560F, 260F)), // 5
            game.choose(Point(610F, 310F), Point(760F, 260F)), // 6
            game.choose(Point(210F, 110F), Point(360F, 60F)), // 7
            game.choose(Point(410F, 110F), Point(560F, 60F)), // 8
            game.choose(Point(610F, 110F), Point(760F, 60F))  // 9
        )

    private val expectedPieceNumberSequence = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    fun initializePuzzle(stage: Stage) {
        puzzle.clear()

        // Teile für den Anfang zufällig durchmischen und drehen
        val randomNumberList: List<Int> = expectedPieceNumberSequence.toMutableList().shuffled()
        randomNumberList.forEachIndexed { index, element ->
            puzzle.add(
                PuzzlePiece(
                    point = positions[index],
                    pieceNumber = element,
                    indexNumber = index,
                    game = game
                ),
            )
        }
        puzzle.forEach { stage.addActor(it) }
    }

    fun exchangePieces(action: PuzzleAction) {
        if (action.changePiece1?.pieceNumber != action.changePiece2?.pieceNumber) {
            val indexPiece1 = action.changePiece1!!.indexNumber
            val indexPiece2 = action.changePiece2!!.indexNumber

            val piece1 = puzzle[indexPiece1]
            val piece2 = puzzle[indexPiece2]

            val piece1Postion = piece1.getPositionAsPoint()
            val piece2Postion = piece2.getPositionAsPoint()

            piece1.indexNumber = indexPiece2
            piece1.setPositionToPoint(piece2Postion)
            piece2.indexNumber = indexPiece1
            piece2.setPositionToPoint(piece1Postion)

            puzzle[indexPiece1] = piece2
            puzzle[indexPiece2] = piece1
        }

        action.reset()
    }

    fun isPuzzleSolved(): Boolean {
        val pieceNumberSequence: List<Int> = puzzle.map { it.pieceNumber }

        // Reihenfolge prüfen
        val correctOrder = pieceNumberSequence == expectedPieceNumberSequence

        // Ausrichtung prüfen
        val correctRotation = puzzle.filter { it.hasCorrectRotation() }.size == 9

        return correctRotation && correctOrder
    }

    fun remove() {
        puzzle.forEach{ it.remove() }
        puzzle.clear()
    }
}
