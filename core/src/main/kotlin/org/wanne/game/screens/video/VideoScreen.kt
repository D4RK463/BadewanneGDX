package org.wanne.game.screens.video

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.video.VideoPlayer
import com.badlogic.gdx.video.VideoPlayerCreator
import org.wanne.game.WanneGame

class VideoScreen(val game: WanneGame, val changeScreen: Screen, private val video: FileHandle): Screen {

    private lateinit var stage: Stage

    private lateinit var videoPlayer: VideoPlayer

    private var videoFinished = false

    // Wird nur für die richtige Sicht beim Desktop Modus benötigt, wird nicht beim Rendern des Videos verwendet.
    private val viewport: FitViewport = FitViewport(1280f, 720f)

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)

        stage = Stage(viewport)
        Gdx.input.inputProcessor = stage

        videoPlayer = VideoPlayerCreator.createVideoPlayer()
        videoPlayer.setOnCompletionListener {
            videoPlayer.stop()
            videoFinished = true
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
        videoFinished = false
        videoPlayer.play()
    }

    override fun render(delta: Float) {
        if (videoPlayer.isBuffered) {
            videoPlayer.volume = game.config.musicVolume
        }

        videoPlayer.update()
        stage.act()

        // Zeichnen
        game.batch.begin()

        val texture: Texture? = videoPlayer.texture
        if (texture != null && !videoFinished) {
            game.batch.draw(
                texture,
                0F,
                0F,
                1280F,
                720F,
                0,
                0,
                1280,
                720,
                false,
                false
            )
        }

        // Im Moment als Workaround, wenn das Video vorbei ist
        if (videoFinished) {
            game.screen = changeScreen
        }

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
