package org.wanne.game.screens.video

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.video.VideoPlayer
import com.badlogic.gdx.video.VideoPlayerCreator
import com.badlogic.gdx.video.scenes.scene2d.VideoActor
import org.wanne.game.WanneGame

class IntoVideoScreen(val game: WanneGame): Screen {

    private lateinit var viewport: FitViewport

    private lateinit var stage: Stage

    private lateinit var videoPlayer: VideoPlayer

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = Stage(viewport)

        videoPlayer = VideoPlayerCreator.createVideoPlayer()
        val actor = VideoActor(videoPlayer)

        stage.addActor(actor)
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
        videoPlayer.dispose()
        stage.dispose()
    }


}
