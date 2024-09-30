package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.screens.AbstractMenuScreen
import kotlin.system.exitProcess

class MenuScreen(
    var game: WanneGame,
) : AbstractMenuScreen(game) {
    private lateinit var stage: Stage

    private var stateTime: Float = 0f

    private var logoHeadline: Texture = game.am.get("pictures/Menue/title.png")

    private var background: Texture = game.am.get("pictures/Menue/background.png")

    private val duck = Duck(200F, 100F, Player.Companion.Looking.RIGHT, am = game.am)

    init {
        duck.scaleUp(0.4F)
        duck.scaleX *= 1.1F
        duck.scaleY *= 1.1F
    }

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        batch = game.batch
        stage = Stage(viewport)

        stage.addActor(Image(background))

        Gdx.input.inputProcessor = stage

        buildMenu()
    }

    private fun buildMenu() {
        val startButton = createTextButton("Singleplayer", 500f, 400f)
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

        val multiButton = createTextButton("Multiplayer", 325f, 250f)
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

        val optionsButton = createTextButton("Optionen", 675f, 250f)
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

        val exitButton = createTextButton("Beenden", 500f, 50f)
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

        batch.projectionMatrix = viewport.camera.combined
        batch.begin()

        stateTime += Gdx.graphics.deltaTime
        val duckSprite: Sprite = duck.getSpriteOfCurrentState(stateTime)
        duckSprite.draw(batch)

        stage.act()
        stage.draw()

        batch.draw(logoHeadline, 140f, 80f)

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
