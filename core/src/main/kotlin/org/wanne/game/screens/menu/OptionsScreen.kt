package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.WanneGame

class OptionsScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    private val musicVolumeLabelText = "Music Volume: "
    private val soundVolumeLabelText = "Sound Volume: "

    override fun buildMenu() {
        val optionsTitle = if (game.currentLang().language == Language.DE) {
            Image(mainButtonAtlas.createSprite("optionen"))
        } else {
            Image(mainButtonAtlas.createSprite("options"))
        }
        optionsTitle.x = 100f
        optionsTitle.y = 550f

        val languageLabel = Label("Sprache auswählen:", game.wanneSkin)
        languageLabel.setPosition(500f, 520f)

        val languageSelectBox = SelectBox<String>(skin)
        languageSelectBox.setPosition(500f, 500F)
        languageSelectBox.setItems("DE_ORIGINAL", "EN", "DROGL") //, "DE_NEU")
        languageSelectBox.selected = game.config.speech
        languageSelectBox.width = 250F
        languageSelectBox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.speech = languageSelectBox.selected
            }
        })

        val soundVolumeLabel = Label("${soundVolumeLabelText}${parsePercent(game.config.soundVolume)}%", game.wanneSkin)
        soundVolumeLabel.setPosition(500f, 420f)

        val soundSlider = Slider(0F, 1F, 0.05F, false, skin)
        soundSlider.setPosition(500f, 400f)
        soundSlider.value = game.config.soundVolume
        soundSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.soundVolume = soundSlider.value
                soundVolumeLabel.setText("")
                soundVolumeLabel.setText("${soundVolumeLabelText}${parsePercent(soundSlider.value)}%")
            }
        })

        val musicVolumeLabel = Label("${musicVolumeLabelText}${parsePercent(game.config.musicVolume)}%", game.wanneSkin)
        musicVolumeLabel.setPosition(500f, 320f)

        val musicSlider = Slider(0F, 1F, 0.05F, false, skin)
        musicSlider.setPosition(500f, 300F)
        musicSlider.value = game.config.musicVolume
        musicSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.musicVolume = musicSlider.value
                musicVolumeLabel.setText("")
                musicVolumeLabel.setText("${musicVolumeLabelText}${parsePercent(musicSlider.value)}%")
            }
        })

        val backButton = createTextButton("Zurück und Speichern", 500f, 80f)
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
        stage.addActor(languageSelectBox)
        stage.addActor(soundVolumeLabel)
        stage.addActor(soundSlider)
        stage.addActor(musicVolumeLabel)
        stage.addActor(musicSlider)
        stage.addActor(backButton)
        stage.addActor(optionsTitle)
    }

    private fun parsePercent(value: Float) : Int {
        return value.times( 10).toInt().times(10)
    }
}
