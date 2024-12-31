package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.Language
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.screens.util.UiButtonBuilder
import java.util.concurrent.TimeUnit
import kotlin.math.min
import kotlin.time.Duration

class ResultScreen(
    game: WanneGame,
) : AbstractMenuScreen(game) {
    // ToDo: Eigenes Bild erstellen
    private var background: Texture = game.am.get("pictures/Menue/background.png")

    private lateinit var title: Label
    private lateinit var titleShadow: Shadow

    private lateinit var timeResult: Label
    private lateinit var timeResultShadow: Shadow

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = Stage(viewport)

        stage.addActor(Image(background))

        val titleString = game.choose("Ergebnis", "Results", false)
        val titlePair = createLabelWithShadow(titleString, 90f, 550f, 1.7F)
        title = titlePair.first
        titleShadow = titlePair.second

        val timeResultPair = createLabelWithShadow(calculateTimeString(), 100f, 450f)
        timeResult = timeResultPair.first
        timeResultShadow = timeResultPair.second

        val backSprite = if (game.currentLang().language == Language.EN) {
            "back"
        } else {
            "zuruck"
        }
        val backButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(backSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(backSprite + "_pressed"))
            .withPoint(Point(500F, 80F))
            .build()
        backButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.arrivedOutside = false
                    game.startedGame = false
                    game.gameEnded = true

                    game.screen = game.mainMenuScreen
                    dispose()
                }
            },
        )

        stage.addActor(titleShadow)
        stage.addActor(timeResultShadow)
        stage.addActor(title)
        stage.addActor(timeResult)
        stage.addActor(backButton)

        Gdx.input.inputProcessor = stage
    }

    private fun calculateTimeString(): String {
        val millis = game.timer.getTime(TimeUnit.MILLISECONDS)
        val minutes = millis / 60000
        val secs = (millis % 60000) / 1000

        return game.choose("Spielzeit: ", "Game Time: ", false)
            .plus(minutes).plus("M ")
            .plus(secs).plus("S")
    }

    override fun dispose() {
        stage.dispose()
    }

}
