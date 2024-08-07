package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.DialogOnlyClickListener
import org.wanne.game.stage.DialogOnlyStage
import org.wanne.model.animation.CowCallAnimation
import org.wanne.model.dialog.DialogBoard
import org.wanne.model.objects.Cow

class CowPhoneScreen(
    private var game: WanneGame,
) : Screen {
    private lateinit var stage: DialogOnlyStage

    private lateinit var viewport: FitViewport

    // Ambience Musik
    private val musicBackground = Gdx.audio.newMusic(Gdx.files.internal("soundsOriginal/Background/Kinderzimmer.mp3"))

    // Animation
    private val cowCallAnimation = CowCallAnimation(0F, 0F)

    // Dialog System
    private val dialogBoard = DialogBoard(skin = game.skin)

    // Objects
    private val cow = Cow(game = game)

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        musicBackground.volume = 0.4F
        musicBackground.isLooping = true
        musicBackground.play()

        stage = DialogOnlyStage(viewport, cowCallAnimation, dialogObject = cow)
        Gdx.input.inputProcessor = stage

        stage.addListener(DialogOnlyClickListener(dialogBoard))

        dialogBoard.isVisible = true
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)

        // Initialen Dialog starten
        stage.currentAction.action(dialogBoard)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Zeichnen
        game.batch.projectionMatrix = viewport.camera.combined
        game.batch.begin()

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
        dialogBoard.dispose()
        stage.dispose()
    }
}
