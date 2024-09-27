package org.wanne.game.stage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.model.ActionType
import org.wanne.game.model.PointAndClickAction
import org.wanne.game.model.animation.CowCallAnimation
import org.wanne.game.model.objects.GameObject

class DialogOnlyStage(
    viewport: Viewport,
    private val cowCallAnimation: CowCallAnimation,
    private val dialogObject: GameObject
) : Stage(
    viewport,
) {
    var currentAction: PointAndClickAction = PointAndClickAction(ActionType.TALK_TO).apply {
        clickedObject = dialogObject
    }

    override fun draw() {
        // Kamera aktualisieren
        val camera = viewport.camera
        camera.update()

        if (!root.isVisible) return

        val batch = this.batch
        batch.projectionMatrix = camera.combined
        batch.begin()

        // Kuh Animation rendern
        cowCallAnimation.draw(batch, Gdx.graphics.deltaTime)

        // Dialog Board rendern
        val children = root.children
        val actors = children.begin()
        actors.filterNotNull().filter { it.isVisible }.forEach { it.draw(batch, 1F) }

        children.end()
        batch.end()
    }
}
