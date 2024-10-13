package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.model.animation.WaterAboveAnimation
import org.wanne.game.screens.AbstractMenuScreen

abstract class AbstractOptionsScreen(game: WanneGame): AbstractMenuScreen(game) {

    lateinit var stage: Stage

    var background: Texture = game.am.get("pictures/Menue/options.png")

    private val waterAnimation = WaterAboveAnimation(230F, 220F, true, game.am)

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        batch = game.batch
        stage = Stage(viewport)

        Gdx.input.inputProcessor = stage

        stage.addActor(Image(background))

        buildMenu()
    }

    abstract fun buildMenu()

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(84 / 255f, 88 / 255f, 92 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewport.apply()

        stage.act()
        stage.draw()

        batch.begin()
        waterAnimation.draw(batch, Gdx.graphics.deltaTime)
        batch.end()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        stage.dispose()
    }
}
