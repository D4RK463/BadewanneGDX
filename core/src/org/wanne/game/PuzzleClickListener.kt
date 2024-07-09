package org.wanne.game

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import org.wanne.model.puzzle.PuzzlePiece

class PuzzleClickListener : ClickListener() {
    override fun clicked(
        event: InputEvent?,
        x: Float,
        y: Float,
    ) {
        val stage = event?.stage

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage?.hit(x, y, true)
        println(hitObject)

        if (hitObject is PuzzlePiece) {
            if (tapCount == 2) {
                println("double click")

                hitObject.rotate90()
            } else {
                println("single click")
            }
        }
    }
}
