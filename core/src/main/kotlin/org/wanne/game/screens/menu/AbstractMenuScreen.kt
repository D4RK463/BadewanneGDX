package org.wanne.game.screens.menu

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame

typealias Shadow = Label

abstract class AbstractMenuScreen (
    val game: WanneGame,
) : Screen {
    lateinit var stage: Stage

    val skin: Skin = game.am.get("ui/default/uiskin.json")

    val mainButtonAtlas: TextureAtlas = game.am.get("pictures/Menue/mainbuttons.atlas")

    lateinit var viewport: FitViewport

    fun createTextButton(
        label: String,
        x: Float,
        y: Float,
        width: Float = 300f,
        height: Float = 100f,
    ): TextButton {
        val button = TextButton(label, skin, "default")
        button.setPosition(x, y)
        button.setSize(width, height)

        return button
    }

    fun createLabelWithShadow(text: String, posX: Float, posY: Float, fontScale: Float = 1F): Pair<Label, Shadow> {
        val label = Label(text, game.wanneSkin)
        label.setPosition(posX, posY)
        label.setFontScale(fontScale)

        val shadow = Label(text, game.wanneSkin)
        shadow.setPosition(posX+2, posY-2)
        shadow.color = Color.BLACK
        shadow.setFontScale(fontScale)

        return Pair(label, shadow)
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
        viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Zeichnen
        game.batch.projectionMatrix = viewport.camera.combined
        game.batch.begin()

        // Stage zeichnen mit UI, Objekten
        stage.act()
        stage.draw()

        game.batch.end()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }
}
