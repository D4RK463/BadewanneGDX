package org.wanne.game.stage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.Viewport
import org.wanne.model.Point
import org.wanne.model.PointAndClickAction
import org.wanne.model.animation.Animation
import org.wanne.model.objects.GameObject
import org.wanne.model.player.Player

class PointAndClickAwareStage(
    viewport: Viewport,
    private val poolAttendant: Player,
    private val duck: Player,
    private val additionalAnimations: List<Animation>?,
) : Stage(
        viewport,
    ) {
    private var stateTime: Float = 0f

    var currentPlayer: Player = poolAttendant

    var moveToPoint: Point? = null
    var needToMove = false

    var currentAction = PointAndClickAction.createDefaultAction()
    var lookingAtTheEnd: Player.Companion.Looking? = null
    var doTheAction: () -> Unit = {}

    fun initializeInventoryItems() {
        // Alle Objekt die im Inventar sind in die aktuelle Stage hinzufügen
        currentAction.inventory.items.forEach { addActor(it) }
    }

    override fun draw() {
        stateTime += Gdx.graphics.deltaTime

        // Bewegung ausrechnen
        if (moveToPoint != null && needToMove) {
            currentPlayer.walkToPoint(
                moveToPoint!!.x,
                moveToPoint!!.y,
                lookingAtTheEnd,
                doTheAction,
            )
        }

        // Kamera aktualisieren
        val camera = viewport.camera
        camera.update()

        if (!root.isVisible) return

        val batch = this.batch
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
        actors.filterIsInstance<GameObject>().filter { it.isVisible }.forEach { it.draw(batch, 1F, stateTime) }

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
