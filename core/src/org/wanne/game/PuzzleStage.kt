package org.wanne.game

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.model.puzzle.Puzzle
import org.wanne.model.puzzle.PuzzleAction

class PuzzleStage(viewport: Viewport, val puzzle: Puzzle): Stage(
    viewport,
) {
    val action = PuzzleAction.getInstance()
}