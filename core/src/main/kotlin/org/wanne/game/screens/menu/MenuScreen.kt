package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.screens.AbstractMenuScreen
import org.wanne.game.stage.MainMenuStage
import kotlin.system.exitProcess

class MenuScreen(
    var game: WanneGame,
) : AbstractMenuScreen(game) {
    private lateinit var stage: Stage

    private var logoHeadline: Image = Image(game.am.get("pictures/Menue/title.png") as Texture)

    private var background: Texture = game.am.get("pictures/Menue/background.png")

    private val duck = Duck(180F, 90F, Player.Companion.Looking.RIGHT, am = game.am)
    private val poolAttendant = PoolAttendant(700F, 95F, Player.Companion.Looking.RIGHT, am = game.am)

    init {
        duck.scaleX *= 1.2F
        duck.scaleY *= 1.2F

        poolAttendant.scaleX *= 1.2F
        poolAttendant.scaleY *= 1.2F
    }

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        batch = game.batch
        stage = MainMenuStage(viewport, poolAttendant, duck, emptyList())

        logoHeadline.x = 140f
        logoHeadline.y = 80f

        stage.addActor(Image(background))
        stage.addActor(logoHeadline)

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
