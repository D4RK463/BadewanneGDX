package org.wanne.game.screens.video

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.video.VideoPlayer
import com.badlogic.gdx.video.VideoPlayerCreator
import com.badlogic.gdx.video.scenes.scene2d.VideoActor
import org.wanne.game.WanneGame

class VideoScreen(val game: WanneGame, val changeScreen: Screen, private val video: FileHandle): Screen {

    private val viewport: FitViewport = FitViewport(1280f, 720f)

    private lateinit var stage: Stage

    private lateinit var videoPlayer: VideoPlayer

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)

        stage = Stage(viewport)
        Gdx.input.inputProcessor = stage

        videoPlayer = VideoPlayerCreator.createVideoPlayer()

//        videoPlayer.volume = game.config.musicVolume
        videoPlayer.setOnCompletionListener {
            dispose()
            game.screen = changeScreen
        }

        stage.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                videoPlayer.stop()
                dispose()
                game.screen = changeScreen
                return true
            }
        })

        videoPlayer.load(video)

        // Actor richtig erstellen (wichtig)
        val actor = VideoActor(videoPlayer)
        actor.x = 0F
        actor.y = 0F
        actor.height = 720F
        actor.width = 1280F
        stage.addActor(actor)

        videoPlayer.play()
    }

    override fun render(delta: Float) {
        viewport.apply()

        // Zeichnen
        game.batch.projectionMatrix = viewport.camera.combined
        game.batch.begin()

        stage.act()
        stage.draw()

        game.batch.end()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        stage.dispose()
        videoPlayer.dispose()
    }

}
