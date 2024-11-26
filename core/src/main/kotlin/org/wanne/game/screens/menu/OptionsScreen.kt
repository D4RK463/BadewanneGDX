package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.WanneGame

class OptionsScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    private val musicVolumeLabelText = "Music: "
    private val soundVolumeLabelText = "Sound: "

    lateinit var optionsTitle: Image
    lateinit var languageLabel: Label
    lateinit var backButton: ImageButton

    override fun buildMenu() {
        val languageSelectBox = SelectBox<String>(skin)
        languageSelectBox.setPosition(650f, 410F)
        languageSelectBox.setItems("DE_ORIGINAL", "EN", "DROGL") //, "DE_NEU")
        languageSelectBox.selected = game.config.speech
        languageSelectBox.width = 350F
        languageSelectBox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.speech = languageSelectBox.selected

                // direkt die Sprache ändern
                optionsTitle.remove()
                languageLabel.remove()
                backButton.remove()

                createLanguageSensitiveObjects()
            }
        })

        val soundVolumeLabel = Label("${soundVolumeLabelText}${parsePercent(game.config.soundVolume)}%", game.wanneSkin)
        soundVolumeLabel.setPosition(100f, 450f)

        val soundSlider = Slider(0F, 1F, 0.05F, false, game.wanneSkin)
        soundSlider.setPosition(100f, 340f)
        soundSlider.width = 390F
        soundSlider.value = game.config.soundVolume
        soundSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.soundVolume = soundSlider.value
                soundVolumeLabel.setText("")
                soundVolumeLabel.setText("${soundVolumeLabelText}${parsePercent(soundSlider.value)}%")
            }
        })

        val musicVolumeLabel = Label("${musicVolumeLabelText}${parsePercent(game.config.musicVolume)}%", game.wanneSkin)
        musicVolumeLabel.setPosition(100f, 250f)

        val musicSlider = Slider(0F, 1F, 0.05F, false, game.wanneSkin)
        musicSlider.setPosition(100f, 140F)
        musicSlider.width = 390F
        musicSlider.value = game.config.musicVolume
        musicSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.musicVolume = musicSlider.value
                musicVolumeLabel.setText("")
                musicVolumeLabel.setText("${musicVolumeLabelText}${parsePercent(musicSlider.value)}%")
            }
        })

        stage.addActor(languageSelectBox)
        stage.addActor(soundVolumeLabel)
        stage.addActor(soundSlider)
        stage.addActor(musicVolumeLabel)
        stage.addActor(musicSlider)

        createLanguageSensitiveObjects()
    }

    private fun createLanguageSensitiveObjects() {
        optionsTitle = if (game.currentLang().language == Language.DE) {
            Image(mainButtonAtlas.createSprite("optionen"))
        } else {
            Image(mainButtonAtlas.createSprite("options"))
        }
        optionsTitle.x = 100f
        optionsTitle.y = 550f


        languageLabel = if (game.currentLang().language == Language.DE) {
            Label("Sprache auswählen", game.wanneSkin)
        } else {
            Label("Choose language", game.wanneSkin)
        }
        languageLabel.setPosition(650f, 450f)

        val backSprite = if (game.currentLang().language == Language.DE) {
            "zuruckspeichern"
        } else {
            "backsave"
        }
        backButton = game.createUIButton(
            mainButtonAtlas.createSprite(backSprite),
            mainButtonAtlas.createSprite(backSprite + "_pressed"),
            300f,
            50f,
        )
        backButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.mainMenuScreen
                    game.config.saveSettings()
                    dispose()
                }
            },
        )

        stage.addActor(languageLabel)
        stage.addActor(backButton)
        stage.addActor(optionsTitle)
    }

    private fun parsePercent(value: Float): Int {
        return value.times(10).toInt().times(10)
    }
}
