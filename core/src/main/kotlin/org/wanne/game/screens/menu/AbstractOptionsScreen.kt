package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.TEXTURES
import org.wanne.game.WanneGame
import org.wanne.game.model.animation.WaterAboveAnimation

abstract class AbstractOptionsScreen(game: WanneGame): AbstractMenuScreen(game) {

    var background: Texture = game.am.get("$TEXTURES/options.png")

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
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        game.batch.projectionMatrix = viewport.camera.combined
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
