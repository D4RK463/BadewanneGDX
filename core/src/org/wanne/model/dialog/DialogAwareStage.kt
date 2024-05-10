package org.wanne.model.dialog

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.model.objects.GameObject
import org.wanne.model.player.Player

class DialogAwareStage(
    viewport: Viewport,
    private val poolAttendant: Player,
    private val duck: Player,
) : Stage(
        viewport,
    ) {
    private var stateTime: Float = 0f

    override fun draw() {
        val camera = viewport.camera
        camera.update()

        if (!root.isVisible) return

        val batch = this.batch
        batch.projectionMatrix = camera.combined
        batch.begin()

        // old school way to render everything at once
//        root.draw(batch, 1f)

        // Objekte und Background rendern
        val children = root.children
        val actors = children.begin()
        val renderedChildren: MutableList<Actor> = mutableListOf()

        actors.filterIsInstance<Image>().forEach {
            run {
                it.draw(batch, 1F)
                renderedChildren.add(it)
            }
        }
        actors.filterIsInstance<GameObject>().forEach { renderedChildren.add(it) }
        actors.filterIsInstance<GameObject>().filter { it.isVisible }.forEach { it.draw(batch, 1F) }

        // Spieler rendern
        // Animationen holen
        stateTime += Gdx.graphics.deltaTime
        val poolAttendantSprite: Sprite = poolAttendant.getSpriteOfCurrentState(Gdx.graphics.deltaTime)
        val duckSprite: Sprite = duck.getSpriteOfCurrentState(stateTime)

        // Spieler in der richtigen Reihenfolge, je nachdem wer gerade vorne steht rendern
        if (poolAttendant.posY < duck.posY) {
            duckSprite.draw(batch)
            poolAttendantSprite.draw(batch)
        } else {
            poolAttendantSprite.draw(batch)
            duckSprite.draw(batch)
        }

        // Den ganzen Rest rendern (Buttons, Tooltips und das Dialog-Brett mit Labels)
        actors.filterNot { renderedChildren.contains(it) }.filterNotNull().filter { it.isVisible }.forEach { it.draw(batch, 1F) }
        children.end()

        batch.end()
    }
}
