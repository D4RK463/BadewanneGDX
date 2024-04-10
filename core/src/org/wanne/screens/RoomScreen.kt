package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import kotlin.system.exitProcess

class RoomScreen(var game: WanneGame) : Screen {
    private var stage: Stage? = null

    private var batch: SpriteBatch? = null

    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))

    // Buttons
    private val exitButton: Texture = Texture(Gdx.files.internal("pictures/Buttons/exit.png"))

    private var viewport: FitViewport? = null

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)

        batch = SpriteBatch()
        stage = Stage()
        Gdx.input.inputProcessor = stage

        viewport = FitViewport(1024f, 768f)

        // Buttons
        val exitButtonTextureRegion = TextureRegion(exitButton)
        val exitButtonRegionDrawable = TextureRegionDrawable(exitButtonTextureRegion)
        val exitButton = ImageButton(exitButtonRegionDrawable)
        exitButton.height = 200f
        exitButton.width = 200f
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    exitProcess(0)
                }
            },
        )

        stage!!.addActor(exitButton)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport!!.apply()

        // Background
        batch!!.projectionMatrix = viewport!!.camera.combined
        batch!!.begin()
        batch!!.draw(roomBackgroundSingle, 0f, 0f)
        batch!!.end()

        stage!!.act()
        stage!!.draw()
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
        viewport!!.update(width, height, true)
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
