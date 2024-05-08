package org.wanne.model.objects

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import org.wanne.model.Point
import org.wanne.model.player.Player
import java.util.*

abstract class GameObject (var posX: Float, var posY: Float): Actor() {

    val itemAtlas: TextureAtlas = TextureAtlas("pictures/Items/items.atlas")

    private val random = Random()

    var stupidAnswers = listOf(
        "Hääh?!?",
        "Was zum Teufel?",
        "Ich kann da nicht bauen!!",
        "Das geht so nicht!",
        "Belästige mich nicht!",
        "Versteh ich nich!!",
        "Wie solln das gehn?",
        "w00t?",
        "Bin doch net blöd!",
        "Hör auf mich zu verwirren!",
        "KLAR...",
        "NATÜRLICH...",
        "Denk doch ma nach!",
        "Funst net!",
        "LANGWEILIG!"
    )

    abstract fun getSprite() : Sprite

    abstract fun look()

    fun use() {
        println(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    fun combine() {
        println(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    fun talk() {
        println(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    fun take() {
        println(stupidAnswers[random.nextInt(stupidAnswers.size - 1)])
    }

    abstract fun getInteractPosition() : Pair<Point,Player.Companion.Looking?>

    fun addPositionToSprite(sprite: Sprite): Sprite {
        sprite.x = posX
        sprite.y = posY
        return sprite
    }

    fun dispose() {
        itemAtlas.dispose()
    }

    override fun getX(): Float {
        return posX
    }

    override fun getY(): Float {
        return posY
    }

    override fun setPosition(x: Float, y: Float) {
        posX = x
        posY = y
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        getSprite().draw(batch, parentAlpha)
    }

    override fun getWidth(): Float {
        return getSprite().width
    }

    override fun getHeight(): Float {
        return getSprite().height
    }

}