package org.wanne.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.screens.game.CowPhoneScreen

class LoadingScreen (
    private var game: WanneGame,
) : Screen {
    private lateinit var viewport: FitViewport

    private lateinit var stage: Stage

    private val loadingLabel: Label = Label("Loading ...", game.wanneSkin)

    private var initialLoadingDone = false
    private var currentLoad = 0
    private var percent = 0F

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = Stage(viewport)

        loadingLabel.isVisible = true
        loadingLabel.setPosition(30F, 10F)

        stage.addActor(loadingLabel)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport.apply()

        // Assets initialisieren
        if (game.am.update(17)) {
            currentLoad+= 1
            when (currentLoad) {
                1 -> {
                    game.am.loadUI()
                    loadingLabel.setText("loading ui (${(percent*100).toInt()}%)")
                }
                2 -> {
                    game.am.loadTextures()
                    loadingLabel.setText("loading textures (${(percent*100).toInt()}%)")
                }
                3 -> {
                    game.am.loadSprites()
                    loadingLabel.setText("loading sprites (${(percent*100).toInt()}%)")
                }
                4 -> {
                    game.am.loadMusic()
                    loadingLabel.setText("loading music (${(percent*100).toInt()}%)")
                }
                5 -> {
                    game.am.loadSounds()
                    loadingLabel.setText("loading sounds (${(percent*100).toInt()}%)")
                }
                else -> {
                    if (currentLoad > 6){
                        percent = 1F

                        if (!initialLoadingDone) {
                            game.cowPhoneScreen = CowPhoneScreen(game)
                            game.initializeItemHolder()
                            loadingLabel.setText("loading complete (${percent*100}%)")

                            game.setScreen(game.mainMenuScreen)
                            initialLoadingDone = true
                        }
                    }
                }
            }

        } else{
            percent = Interpolation.linear.apply(percent, game.am.progress(), 0.05f)
        }

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
    }

}
