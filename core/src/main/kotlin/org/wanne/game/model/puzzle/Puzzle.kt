package org.wanne.game.model.puzzle

import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.game.AssetsManager
import org.wanne.game.model.Point

class Puzzle(private val am: AssetsManager) {
    private val puzzle: MutableList<PuzzlePiece> = ArrayList()

    private val positions =
        arrayOf(
            org.wanne.game.model.Point(210, 510), // 1
            org.wanne.game.model.Point(410, 510), // 2
            org.wanne.game.model.Point(610, 510), // 3
            org.wanne.game.model.Point(210, 310), // 4
            org.wanne.game.model.Point(410, 310), // 5
            org.wanne.game.model.Point(610, 310), // 6
            org.wanne.game.model.Point(210, 110), // 7
            org.wanne.game.model.Point(410, 110), // 8
            org.wanne.game.model.Point(610, 110), // 9
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
                    am = am
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
}
