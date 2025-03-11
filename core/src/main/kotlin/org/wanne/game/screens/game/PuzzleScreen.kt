package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.MUSIC
import org.wanne.game.SPRITES
import org.wanne.game.TEXTURES
import org.wanne.game.WanneGame
import org.wanne.game.listener.PuzzleClickListener
import org.wanne.game.model.Point
import org.wanne.game.model.puzzle.Puzzle
import org.wanne.game.model.puzzle.VictoryMessage
import org.wanne.game.screens.AbstractScreen
import org.wanne.game.screens.util.UiButtonBuilder
import org.wanne.game.stage.PuzzleStage

class PuzzleScreen(
    game: WanneGame,
) : AbstractScreen(game) {
    // Background
    private val puzzleBackgroundSingle: Texture = game.am.get("$TEXTURES/puzzle.png")
    private val puzzleBackgroundSingleWide: Texture = game.am.get("$TEXTURES/puzzle169.png")

    private lateinit var stage: PuzzleStage

    private lateinit var viewport: FitViewport

    // Objects
    private lateinit var puzzle: Puzzle
    private lateinit var victoryMessage: VictoryMessage

    // Buttons
    private val buttonAtlas: TextureAtlas = game.am.get("$SPRITES/buttons.atlas")
    private lateinit var exitButton: ImageButton

    // Ambience Musik
    private val musicBackground: Music = game.am.get("$MUSIC/puzzle_theme.mp3")

    override fun show() {
        createPuzzle()
        createGameObjects()

        Gdx.graphics.setWindowedMode(game.config.getResolutionX(), game.config.getResolutionY())
        viewport = FitViewport(game.config.getResolutionX().toFloat(), game.config.getResolutionY().toFloat())

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        stage = PuzzleStage(viewport, puzzle, game)
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.classicMode()) {
            stage.addActor(Image(puzzleBackgroundSingle))
        } else {
            stage.addActor(Image(puzzleBackgroundSingleWide))
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
        val action = if (game.android) { "tab" } else { game.choose("klick", "click", false) }
        val instructionsTextDuck = game.choose("Drehen = Puzzleteil doppelt ${action}en", "To turn = double $action")
        val instructionsTextAttendant = game.choose("Tauschen = 2 Puzzleteile ${action}en", "To switch = $action 2 pieces")
        val instructionsDuckPair = createLabelWithShadow(instructionsTextDuck, 150f, 50f)
        val instructionsDuck = instructionsDuckPair.first
        val instructionsDuckShadow = instructionsDuckPair.second
        val instructionsAttendantPair = createLabelWithShadow(instructionsTextAttendant, 150f, 10f)
        val instructionsAttendant = instructionsAttendantPair.first
        val instructionsAttendantShadow = instructionsAttendantPair.second

        exitButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("exit"))
            .withTexturePressed(buttonAtlas.createSprite("exitPressed"))
            .withPoint(game.choose(Point(980F, 705F), Point(1200F, 640F)))
            .withTooltip(TextTooltip(game.choose("bring mich zurück", "bring me back"), game.currentSkin()))
            .useScaling(!game.classicMode())
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

        stage.addActor(instructionsDuckShadow)
        stage.addActor(instructionsAttendantShadow)
        stage.addActor(instructionsDuck)
        stage.addActor(instructionsAttendant)
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
