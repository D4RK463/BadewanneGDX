package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.screens.AbstractMenuScreen
import kotlin.system.exitProcess

class MenuScreen(
    var game: WanneGame,
) : AbstractMenuScreen(game) {
    private lateinit var stage: Stage

    private var logoHeadline: Texture = game.am.get("pictures/Menue/header.png")

    private var duck: Texture = game.am.get("pictures/Menue/ente.png")

    private var poolAttendant: Texture = game.am.get("pictures/Menue/bademeister.png")

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = game.batch
        stage = Stage(viewport)

        Gdx.input.inputProcessor = stage

        buildMenu()
    }

    private fun buildMenu() {
        val startButton = createTextButton("Singleplayer", 350f, 550f)
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

        val multiButton = createTextButton("Multiplayer", 350f, 400f)
        multiButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.networkScreen
                    dispose()
                }
            },
        )

        val optionsButton = createTextButton("Optionen", 350f, 250f)
        optionsButton.isDisabled = true
        optionsButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Not yet implemented!")
                }
            },
        )

        val exitButton = createTextButton("Beenden", 350f, 100f)
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
        stage.addActor(optionsButton)
        stage.addActor(exitButton)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(84 / 255f, 88 / 255f, 92 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewport.apply()

        stage.act()
        stage.draw()

        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        batch.draw(logoHeadline, 400f, 700f)
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
        stage.dispose()
    }

    override fun hide() {
    }
}
