package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import org.wanne.game.WanneGame
import kotlin.system.exitProcess

class MenuScreen(
    var game: WanneGame,
) : Screen {
    private lateinit var stage: Stage

    private lateinit var batch: SpriteBatch

    private val skin: Skin = game.am.get("ui/uiskin.json")

    private var logoHeadline: Texture = game.am.get("pictures/Menue/header.png")

    private var duck: Texture = game.am.get("pictures/Menue/ente.png")

    private var poolAttendant: Texture = game.am.get("pictures/Menue/bademeister.png")

    private lateinit var viewport: ScreenViewport

    override fun show() {
        batch = SpriteBatch()
        stage = Stage()

        Gdx.input.inputProcessor = stage
        viewport = ScreenViewport()

        buildMenu()
    }

    private fun buildMenu() {
        val startButton = createTextButton("Singleplayer", 400f, 450f)
        startButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.roomScreen
                    dispose()
                }
            },
        )

        val multiButton = createTextButton("Multiplayer", 400f, 350f)
        multiButton.isDisabled = true
        multiButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Not Implemented... yet :)")
                }
            },
        )

        val exitButton = createTextButton("Beenden", 400f, 250f)
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

        stage.addActor(startButton)
        stage.addActor(multiButton)
        stage.addActor(exitButton)
    }

    private fun createTextButton(
        label: String,
        x: Float,
        y: Float,
    ): TextButton {
        val button = TextButton(label, skin, "default")
        button.setPosition(x, y)
        button.setSize(210f, 60f)

        return button
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(84 / 255f, 88 / 255f, 92 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewport.apply()

        stage.act()
        stage.draw()

        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        batch.draw(logoHeadline, 400f, 600f)
        batch.draw(duck, 715f, 243f)
        batch.draw(poolAttendant, 110f, 243f)
        batch.end()
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        batch.dispose()
        stage.dispose()
    }

    override fun hide() {
    }
}
