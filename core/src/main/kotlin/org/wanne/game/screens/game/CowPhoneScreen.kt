package org.wanne.game.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.listener.DialogOnlyClickListener
import org.wanne.game.model.Point
import org.wanne.game.model.animation.CowCallAnimation
import org.wanne.game.model.dialog.DialogBoard
import org.wanne.game.model.objects.Cow
import org.wanne.game.stage.DialogOnlyStage

class CowPhoneScreen(
    private var game: WanneGame,
) : Screen {
    private lateinit var stage: DialogOnlyStage

    private lateinit var viewport: FitViewport

    // Background (nur für Modern Mode)
    private val roomBackgroundSingleWide: Texture = game.am.get("pictures/Backgrounds/Kinderzimmer169.png")

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/Kinderzimmer.mp3")

    // Animation
    private lateinit var cowCallAnimation: CowCallAnimation

    // Dialog System
    private val dialogBoard = DialogBoard(game = game)

    // Objects
    private lateinit var cow: Cow

    override fun show() {
        createAnimation()
        createGameObjects()

        Gdx.graphics.setWindowedMode(game.config.getResolutionX(), game.config.getResolutionY())
        viewport = FitViewport(game.config.getResolutionX().toFloat(), game.config.getResolutionY().toFloat())

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        stage = DialogOnlyStage(viewport, cowCallAnimation, dialogObject = cow)
        Gdx.input.inputProcessor = stage

        stage.addListener(DialogOnlyClickListener(dialogBoard))

        if (!game.classicMode()) {
            stage.addActor(Image(roomBackgroundSingleWide))
        }

        dialogBoard.isVisible = true
        stage.addActor(dialogBoard)
        dialogBoard.initialize(stage)

        // Initialen Dialog starten
        stage.currentAction.action(dialogBoard)
    }

    private fun createAnimation() {
        cowCallAnimation = CowCallAnimation(game.choose(Point(0F, 0F), Point(254F, 0F)), am = game.am)
    }

    private fun createGameObjects() {
        cow = Cow(game = game)
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
        dialogBoard.remove()
        cow.remove()
        musicBackground.stop()
    }

    override fun dispose() {
        stage.dispose()
    }
}
