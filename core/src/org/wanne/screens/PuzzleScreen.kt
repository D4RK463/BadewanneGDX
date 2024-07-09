package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.PuzzleClickListener
import org.wanne.game.WanneGame
import org.wanne.model.Point
import org.wanne.model.puzzle.Puzzle
import org.wanne.model.puzzle.PuzzlePiece

class PuzzleScreen(
    var game: WanneGame,
) : Screen {
    // Background
    private val puzzleBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/puzzle.png"))
    private val puzzleBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/puzzleMultiplayer.png"))

    private lateinit var stage: Stage

    private lateinit var batch: SpriteBatch

    private lateinit var viewport: FitViewport

    private val puzzle = Puzzle()

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = SpriteBatch()

        stage = Stage()
        Gdx.input.inputProcessor = stage

        // Hintergrund setzen
        if (game.isSingleplayer) {
            stage.addActor(Image(puzzleBackgroundSingle))
        } else {
            stage.addActor(Image(puzzleBackgroundMulti))
        }

        stage.addListener(PuzzleClickListener())

        createGameUI()

        puzzle.initializePuzzle(stage)
    }

    private fun createGameUI() {
        val exitButton =
            game.createUIButton(
                game.buttonAtlas.createSprite("exit"),
                game.buttonAtlas.createSprite("exitPressed"),
                980f,
                705f,
            )
        exitButton.addListener(TextTooltip("bring mich zurück", game.skin))
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
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()

        // Stage zeichnen
        stage.act()
        stage.draw()

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

    override fun hide() {
    }

    override fun dispose() {
        batch.dispose()
        stage.dispose()
    }
}
