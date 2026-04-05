package org.wanne.game.screens.menu

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.Statistic
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.screens.util.UiButtonBuilder
import java.util.concurrent.TimeUnit

class ResultScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    override fun buildMenu() {
        val titleString = game.choose("Ergebnis", "Results", false)
        val titlePair = createLabelWithShadow(titleString, 90f, 550f, 1.7F)
        val title = titlePair.first
        val titleShadow = titlePair.second

        val lookResultString = game.choose("Ansehen: ${Statistic.lookCount}x", "look: ${Statistic.lookCount}x", false)
        val lookResultPair = createLabelWithShadow(lookResultString, 100f, 450f)
        val lookResult = lookResultPair.first
        val lookResultShadow = lookResultPair.second

        val useResultString = game.choose("Benutzen: ${Statistic.useCount+2}x", "use: ${Statistic.useCount+2}x", false)
        val useResultPair = createLabelWithShadow(useResultString, 500f, 450f)
        val useResult = useResultPair.first
        val useResultShadow = useResultPair.second

        val talkResultString = game.choose("Reden: ${Statistic.talkCount}x", "talk: ${Statistic.talkCount}x", false)
        val talkResultPair = createLabelWithShadow(talkResultString, 100f, 400f)
        val talkResult = talkResultPair.first
        val talkResultShadow = talkResultPair.second

        val combineResultString = game.choose("Kombinieren: ${Statistic.combineCount}x", "combine: ${Statistic.combineCount}x", false)
        val combineResultPair = createLabelWithShadow(combineResultString, 500f, 400f)
        val combineResult = combineResultPair.first
        val combineResultShadow = combineResultPair.second

        val takeResultString = game.choose("Nehmen: ${Statistic.takeCount}x", "take: ${Statistic.takeCount}x", false)
        val takeResultPair = createLabelWithShadow(takeResultString, 100f, 350f)
        val takeResult = takeResultPair.first
        val takeResultShadow = takeResultPair.second

        val clicksResultString = game.choose("Gesamt Klicks: ${Statistic.clicks}", "total clicks: ${Statistic.clicks}", false)
        val clicksResultPair = createLabelWithShadow(clicksResultString, 100f, 250f, color = Color.ORANGE)
        val clicksResult = clicksResultPair.first
        val clicksResultShadow = clicksResultPair.second

        val timeResultPair = createLabelWithShadow(calculateTimeString(), 100f, 200f, color = Color.ORANGE)
        val timeResult = timeResultPair.first
        val timeResultShadow = timeResultPair.second

        // Was freigeschaltet wurde?
        var somethingWasUnlocked = false
        if (game.unlockDrogglWithNewPlaythrough) {
            val drogglString = game.choose("Droggelbecher Modus", "Droggelbecher Mode", false)
            val drogglPair = createLabelWithShadow(drogglString, 550f, 250f, 1.2F)
            val droggl = drogglPair.first
            val drogglShadow = drogglPair.second

            stage.addActor(drogglShadow)
            stage.addActor(droggl)

            game.unlockDrogglWithNewPlaythrough = false
            game.config.drooglUnlocked = true
            game.config.saveSettings()

            somethingWasUnlocked = true
        }

        // Wenn was freigeschaltet wurde?
        if (somethingWasUnlocked) {
            val unlockString = game.choose("freigeschaltet", "unlocked", false)
            val unlockPair = createLabelWithShadow(unlockString, 550f, 200f, 1.2F)
            val unlock = unlockPair.first
            val unlockShadow = unlockPair.second

            stage.addActor(unlockShadow)
            stage.addActor(unlock)
        }


        val backSprite = if (game.currentLang().language == Language.EN || game.currentLang().language == Language.DROGL ) {
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
                    Statistic.reset()

                    game.screen = game.mainMenuScreen
                    dispose()
                }
            },
        )

        stage.addActor(titleShadow)
        stage.addActor(timeResultShadow)
        stage.addActor(lookResultShadow)
        stage.addActor(useResultShadow)
        stage.addActor(talkResultShadow)
        stage.addActor(combineResultShadow)
        stage.addActor(takeResultShadow)
        stage.addActor(clicksResultShadow)
        stage.addActor(title)
        stage.addActor(timeResult)
        stage.addActor(lookResult)
        stage.addActor(useResult)
        stage.addActor(talkResult)
        stage.addActor(combineResult)
        stage.addActor(takeResult)
        stage.addActor(clicksResult)
        stage.addActor(backButton)
    }

    private fun calculateTimeString(): String {
        val millis = game.timer.getTime(TimeUnit.MILLISECONDS)
        val minutes = millis / 60000
        val secs = (millis % 60000) / 1000

        return game.choose("Spielzeit: ", "Game Time: ", false)
            .plus(minutes).plus("M ")
            .plus(secs).plus("S")
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

}
