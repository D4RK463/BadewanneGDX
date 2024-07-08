package org.wanne.game

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class PuzzleClickListener : ClickListener() {
    override fun clicked(
        event: InputEvent?,
        x: Float,
        y: Float,
    ) {
        val stage = event?.stage

        // Das Objekt holen, auf welches geklickt wurde
        val hitObject = stage?.hit(x, y, true)

        if (tapCount == 2) {
            println("double click")
        } else {
            println("single click")
        }
    }
}
