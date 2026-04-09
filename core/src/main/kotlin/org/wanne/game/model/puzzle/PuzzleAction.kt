package org.wanne.game.model.puzzle

class PuzzleAction{
    companion object {

        private var instance: PuzzleAction? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PuzzleAction().also { instance = it }
            }
    }

    var changePiece1: PuzzlePiece? = null

    var changePiece2: PuzzlePiece? = null

    fun reset() {
        changePiece1 = null
        changePiece2 = null
    }

    fun setChangePieces(puzzlePiece: PuzzlePiece): Boolean {
        if (changePiece1 == null) {
            changePiece1 = puzzlePiece
            return false
        } else if (changePiece2 == null) {
            changePiece2 = puzzlePiece
            return true
        } else {
            return true
        }
    }

}
