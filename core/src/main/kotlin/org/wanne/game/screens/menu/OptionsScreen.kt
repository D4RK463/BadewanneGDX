package org.wanne.game.screens.menu

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.WanneGame

class OptionsScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    private val musicVolumeLabelText = "Music Volume: "
    private val soundVolumeLabelText = "Sound Volume: "

    override fun buildMenu() {

        val languageLabel = Label("Sprache auswählen:", skin)
        languageLabel.setPosition(350f, 520f)
        languageLabel.color = Color.BLACK

        val languageSelectBox = SelectBox<String>(skin)
        languageSelectBox.setPosition(350F, 500F)
        languageSelectBox.setItems("DE_ORIGINAL") //, "DE_NEU", "EN", "DROGL")
        languageSelectBox.selected = game.config.speech
        languageSelectBox.width = 250F
        languageSelectBox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.speech = languageSelectBox.selected
            }
        })

        val soundVolumeLabel = Label("${soundVolumeLabelText}${game.config.soundVolume}", skin)
        soundVolumeLabel.setPosition(350f, 420f)
        soundVolumeLabel.color = Color.BLACK

        val soundSlider = Slider(0F, 1F, 0.05F, false, skin)
        soundSlider.setPosition(350F, 400F)
        soundSlider.value = game.config.soundVolume
        soundSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.soundVolume = soundSlider.value
                soundVolumeLabel.setText("")
                soundVolumeLabel.setText("${soundVolumeLabelText}${soundSlider.value}")
            }
        })

        val musicVolumeLabel = Label("${musicVolumeLabelText}${game.config.musicVolume}", skin)
        musicVolumeLabel.setPosition(350f, 320f)
        musicVolumeLabel.color = Color.BLACK

        val musicSlider = Slider(0F, 1F, 0.05F, false, skin)
        musicSlider.setPosition(350F, 300F)
        musicSlider.value = game.config.musicVolume
        musicSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.musicVolume = musicSlider.value
                musicVolumeLabel.setText("")
                musicVolumeLabel.setText("${musicVolumeLabelText}${musicSlider.value}")
            }
        })

        val backButton = createTextButton("Zurück und Speichern", 350f, 80f)
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
    }
}
