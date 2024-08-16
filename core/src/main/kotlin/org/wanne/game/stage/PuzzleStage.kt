package org.wanne.game.stage

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.WanneGame
import org.wanne.model.puzzle.Puzzle
import org.wanne.model.puzzle.PuzzleAction
import org.wanne.model.puzzle.VictoryMessage

class PuzzleStage(
    viewport: Viewport,
    val puzzle: Puzzle,
    val victoryMessage: VictoryMessage,
    val game: WanneGame,
) : Stage(
        viewport,
    ) {
    val action = PuzzleAction.getInstance()
}
