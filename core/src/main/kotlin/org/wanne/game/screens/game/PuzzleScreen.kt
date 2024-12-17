package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.screens.util.UiButtonBuilder
import org.wanne.game.WanneGame
import org.wanne.game.listener.PuzzleClickListener
import org.wanne.game.model.Point
import org.wanne.game.stage.PuzzleStage
import org.wanne.game.model.puzzle.Puzzle
import org.wanne.game.model.puzzle.VictoryMessage

class PuzzleScreen(
    var game: WanneGame,
) : Screen {
    // Background
    private val puzzleBackgroundSingle: Texture = game.am.get("pictures/Backgrounds/puzzle.png")
    private val puzzleBackgroundSingleWide: Texture = game.am.get("pictures/Backgrounds/puzzle169.png")
    private val puzzleBackgroundMulti: Texture = game.am.get("pictures/Backgrounds/puzzleMultiplayer.png")

    private lateinit var stage: PuzzleStage

    private lateinit var viewport: FitViewport

    // Objects
    private lateinit var puzzle: Puzzle
    private lateinit var victoryMessage: VictoryMessage

    // Buttons
    private val buttonAtlas: TextureAtlas = game.am.get("pictures/Buttons/buttons.atlas")
    private lateinit var exitButton: ImageButton

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/jeopardy.mp3")

    override fun show() {
        createPuzzle()
        createGameObjects()

        Gdx.graphics.setWindowedMode(game.config.getResolutionX(), game.config.getResolutionY())
        viewport = FitViewport(game.config.getResolutionX().toFloat(), game.config.getResolutionY().toFloat())

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        stage = PuzzleStage(viewport, puzzle, victoryMessage, game)
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            if (game.classicMode()) {
                stage.addActor(Image(puzzleBackgroundSingle))
            } else {
                stage.addActor(Image(puzzleBackgroundSingleWide))
            }
        } else {
            stage.addActor(Image(puzzleBackgroundMulti))
        }

        stage.addListener(PuzzleClickListener())

        puzzle.initializePuzzle(stage)
        stage.addActor(victoryMessage)

        createGameUI()
    }

    private fun createPuzzle() {
        puzzle = Puzzle(game)
    }

    private fun createGameObjects() {
        victoryMessage = VictoryMessage(game = game)
        victoryMessage.isVisible = false
    }

    private fun createGameUI() {
        exitButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("exit"))
            .withTexturePressed(buttonAtlas.createSprite("exitPressed"))
            .withPoint(game.choose(Point(980F, 705F), Point(1200F, 640F)))
            .withTooltip(TextTooltip(game.choose("bring mich zurück", "bring me back"), game.currentSkin()))
            .build()
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.roomScreen
                }
            },
        )

        stage.addActor(exitButton)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Zeichnen
        game.batch.projectionMatrix = viewport.camera.combined
        game.batch.begin()

        // Stage zeichnen
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

    override fun hide() {
        musicBackground.stop()
        puzzle.remove()
        victoryMessage.remove()
        exitButton.remove()
    }

    override fun dispose() {
        stage.dispose()
    }
}
