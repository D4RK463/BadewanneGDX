package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import org.wanne.game.WanneGame

class RoomScreen(var game: WanneGame) : Screen {
    private var stage: Stage? = null

    private var batch: SpriteBatch? = null

    private var roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))

    private var camera: OrthographicCamera? = null

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)

        batch = SpriteBatch()
        stage = Stage()
        Gdx.input.inputProcessor = stage

        camera = OrthographicCamera()
//        camera!!.setToOrtho(false, FloatGdx.graphics.width / 2, 600f)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera!!.update()

        batch!!.begin()
//        batch!!.draw(roomBackgroundSingle, 0f, 0f, 0f, 0f, 1024f, 768f, 800f, 600f, 0f, 0, 0, 1024, 768, false, false)
        batch!!.draw(roomBackgroundSingle, 0f, 0f)
        batch!!.end()
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch!!.dispose()
    }
}
