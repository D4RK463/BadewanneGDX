package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.model.animation.WaterAnimation
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.screens.AbstractMenuScreen
import org.wanne.game.stage.MainMenuStage
import kotlin.system.exitProcess

class MainMenuScreen(
    game: WanneGame,
) : AbstractMenuScreen(game) {
    private lateinit var stage: Stage

    private var logoHeadline: Image = Image(game.am.get("pictures/Menue/title3.png") as Texture)
    private var edge: Image = Image(game.am.get("pictures/Menue/ecke.png") as Texture)

    private var background: Texture = game.am.get("pictures/Menue/background.png")

    private val duck = Duck(485F, 90F, Player.Companion.Looking.RIGHT, am = game.am)
    private val poolAttendant = PoolAttendant(700F, 95F, Player.Companion.Looking.RIGHT, am = game.am)

    private val buttonAtlas: TextureAtlas = game.am.get("pictures/Buttons/buttons.atlas")

    private val waterAnimation = WaterAnimation(938F, 170F, true, game.am)

    init {
        duck.scaleX *= 1.2F
        duck.scaleY *= 1.2F

        poolAttendant.scaleX *= 1.4F
        poolAttendant.scaleY *= 1.4F
    }

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        batch = game.batch
        stage = MainMenuStage(viewport, poolAttendant, duck, listOf(waterAnimation))

        logoHeadline.x = 100f
        logoHeadline.y = 550f

        edge.x = 1158F
        edge.y = 573F

        stage.addActor(Image(background))
        stage.addActor(logoHeadline)

        if (!game.android) {
            stage.addActor(edge)
        }

        Gdx.input.inputProcessor = stage

        buildMenu()
    }

    private fun buildMenu() {
        val singlePlayerButtonText = if (game.startedGame) {
            "Continue"
        } else {
            "Singleplayer"
        }

        val startButton = createTextButton(singlePlayerButtonText, 100f, 400f)
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

        val multiButton = createTextButton("Multiplayer", 100f, 250f)
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

        val optionsButton = createTextButton("Optionen", 100f, 100f)
        optionsButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.optionsScreen
                    dispose()
                }
            },
        )

        val exitButton =
            game.createUIButton(
                buttonAtlas.createSprite("exit"),
                buttonAtlas.createSprite("exitPressed"),
                1235f,
                655f,
            )
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

        if (!game.android) {
            stage.addActor(exitButton)
        }
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
