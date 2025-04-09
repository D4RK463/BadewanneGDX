package org.wanne.game.stage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.game.model.animation.Animation
import org.wanne.game.model.objects.GameObject
import org.wanne.game.model.player.Player
import org.wanne.game.model.shader.Glow.shader

abstract class AbstractAnimationStage(
    viewport: Viewport,
    private val poolAttendant: Player,
    private val duck: Player,
    private val additionalAnimations: List<Animation>?,
    ) : Stage(viewport) {

    private var stateTime: Float = 0f

    fun drawTheWorld() {
        stateTime += Gdx.graphics.deltaTime

        // Kamera aktualisieren
        val camera = viewport.camera
        camera.update()

        if (!root.isVisible) return

        batch.projectionMatrix = camera.combined
        batch.begin()

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
//        actors.filterIsInstance<GameObject>().filter { it.isVisible }.forEach { it.draw(batch, 1F, Gdx.graphics.deltaTime) }

        actors.filterIsInstance<GameObject>().filter {
            it.isVisible
        }.forEach {
            if (it.glow) {

                // Normalen Render enden
                batch.end()
                batch.flush()

                // Object mit Shader zeichnen
                batch.begin()
                batch.shader = shader

                shader.bind()
//                shader.setUniformf("u_amount", 1.5f)
//                shader.setUniformf("u_speed", 0.5f)
//                shader.setUniformf("u_time", stateTime)
//                shader.setUniformf("u_resolution", 1280F, 7200F)
//                shader.setUniformi("u_texture", 0)
//                shader.setUniformf("center", it.posX, it.posY);

                it.draw(batch, 1F, Gdx.graphics.deltaTime)
                batch.end()
                batch.flush()

                // Shader wieder entfernen und alles geht normal weiter
                batch.shader = null
                batch.begin()

            } else {
                it.draw(batch, 1F, Gdx.graphics.deltaTime)
            }
        }

        // Zusätzliche Animationen rendern
        additionalAnimations?.forEach {
            it.draw(batch, Gdx.graphics.deltaTime)
        }

        // Spieler rendern
        // Animationen holen
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
        actors
            .filterNot { renderedChildren.contains(it) }
            .filterNotNull()
            .filter { it.isVisible }
            .forEach { it.draw(batch, 1F) }

        children.end()
        batch.end()
    }
}
