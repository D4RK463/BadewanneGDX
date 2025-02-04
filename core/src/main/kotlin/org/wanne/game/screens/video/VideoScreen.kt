package org.wanne.game.screens.video

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.video.VideoPlayer
import com.badlogic.gdx.video.VideoPlayerCreator
import ktx.app.clearScreen
import org.wanne.game.WanneGame
import kotlin.io.path.fileVisitor

class VideoScreen(val game: WanneGame, val changeScreenName: String, private val video: FileHandle): Screen {

    private val viewport: FitViewport = FitViewport(1280f, 720f)

    private lateinit var stage: Stage

    private lateinit var videoPlayer: VideoPlayer

    private lateinit var frameBuffer: FrameBuffer

    private var videoFinished = false

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
                game.changeScreenAfterVideo(changeScreenName)
                return true
            }
        })

        videoPlayer.load(video)

        // Bei Android braucht's einen Framebuffer, warum auch immer
        if (game.android) {
            frameBuffer = FrameBuffer(Pixmap.Format.RGB565, 1280, 720, false)
        }

        videoFinished = false
        videoPlayer.play()
    }

    override fun render(delta: Float) {
        viewport.apply()

        if (videoPlayer.isBuffered) {
            videoPlayer.volume = game.config.musicVolume
        }

        if (game.android) {
            frameBuffer.begin()
        }

        // Zeichnen
        game.batch.projectionMatrix = viewport.camera.combined
        game.batch.begin()

        videoPlayer.update()

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
            game.changeScreenAfterVideo(changeScreenName)
        }

        stage.act()
        stage.draw()
        game.batch.end()

        if (game.android) {
            frameBuffer.end()
        }
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
