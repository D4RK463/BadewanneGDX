package org.wanne.game.network.model

import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.puzzle.PuzzleAction

class ActionWrapper {
    var pointAndClickAction: PointAndClickAction? = null

    var puzzleAction: PuzzleAction? = null

    var x: Float = 0f
    var y: Float = 0f
}
