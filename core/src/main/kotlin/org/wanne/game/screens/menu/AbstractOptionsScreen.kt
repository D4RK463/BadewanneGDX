package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.model.animation.WaterAboveAnimation

abstract class AbstractOptionsScreen(game: WanneGame): AbstractMenuScreen(game) {

    var background: Texture = game.am.get("textures/options.png")

    private val waterAnimation = WaterAboveAnimation(235F, 225F, true, game.am)

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = Stage(viewport)

        Gdx.input.inputProcessor = stage

        buildMenu()
    }

    abstract fun buildMenu()

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(84 / 255f, 88 / 255f, 92 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewport.apply()

        game.batch.begin()

        game.batch.draw(background, 0F, 0F)
        waterAnimation.draw(game.batch, Gdx.graphics.deltaTime)
        game.batch.end()

        stage.act()
        stage.draw()

    }

    override fun dispose() {
        stage.dispose()
    }
}
