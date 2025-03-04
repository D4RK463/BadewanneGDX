package org.wanne.game.screens.menu

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.screens.AbstractScreen

typealias Shadow = Label

abstract class AbstractMenuScreen (
    game: WanneGame,
) : AbstractScreen(game) {
    lateinit var stage: Stage

    val skin: Skin = game.am.get("skins/default/uiskin.json")

    val mainButtonAtlas: TextureAtlas = game.am.get("textures/mainbuttons.atlas")

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
