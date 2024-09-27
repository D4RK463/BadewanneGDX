package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.PuzzleClickListener
import org.wanne.game.stage.PuzzleStage
import org.wanne.game.model.puzzle.Puzzle
import org.wanne.game.model.puzzle.VictoryMessage

class PuzzleScreen(
    var game: WanneGame,
) : Screen {
    private val skin: Skin = game.am.get("ui/uiskin.json")

    // Background
    private val puzzleBackgroundSingle: Texture = game.am.get("pictures/Backgrounds/puzzle.png")
    private val puzzleBackgroundMulti: Texture = game.am.get("pictures/Backgrounds/puzzleMultiplayer.png")

    private lateinit var stage: PuzzleStage

    private lateinit var viewport: FitViewport

    private val puzzle = Puzzle(game.am)

    private val victoryMessage = org.wanne.game.model.puzzle.VictoryMessage(am = game.am)

    private val buttonAtlas: TextureAtlas = game.am.get("pictures/Buttons/buttons.atlas")

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/jeopardy.mp3")

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        musicBackground.volume = 0.2F
        musicBackground.isLooping = true
        musicBackground.play()

        stage = PuzzleStage(viewport, puzzle, victoryMessage, game)
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(puzzleBackgroundSingle))
        } else {
            stage.addActor(Image(puzzleBackgroundMulti))
        }

        stage.addListener(PuzzleClickListener())

        puzzle.initializePuzzle(stage)

        victoryMessage.isVisible = false
        stage.addActor(victoryMessage)

        createGameUI()
    }

    private fun createGameUI() {
        val exitButton =
            game.createUIButton(
                buttonAtlas.createSprite("exit"),
                buttonAtlas.createSprite("exitPressed"),
                980f,
                705f,
            )
        exitButton.addListener(TextTooltip("bring mich zurück", skin))
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
    }

    override fun dispose() {
        stage.dispose()
    }
}
