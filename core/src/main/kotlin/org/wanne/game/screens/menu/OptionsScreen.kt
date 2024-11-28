package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.WanneGame
import org.wanne.game.sound.Speech

class OptionsScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    private val musicVolumeLabelText = "Music: "
    private val soundVolumeLabelText = "Sound: "

    lateinit var optionsTitle: Image
    lateinit var languageLabel: Label
    lateinit var modeLabel: Label
    lateinit var backButton: ImageButton

    lateinit var gerButton: ImageButton
    lateinit var engButton: ImageButton
    lateinit var orgButton: ImageButton
    lateinit var droglButton: ImageButton

    override fun buildMenu() {
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

        stage.addActor(soundVolumeLabel)
        stage.addActor(soundSlider)
        stage.addActor(musicVolumeLabel)
        stage.addActor(musicSlider)

        createLangButtons()
        createLanguageSensitiveObjects()
    }

    private fun updateLanguageSensitiveObjects() {
        optionsTitle.remove()
        languageLabel.remove()
        modeLabel.remove()
        backButton.remove()

        gerButton.remove()
        engButton.remove()
        orgButton.remove()
        droglButton.remove()

        createLanguageSensitiveObjects()
        createLangButtons()
    }

    private fun createLanguageSensitiveObjects() {
        optionsTitle = if (game.currentLang().language == Language.DE) {
            Image(mainButtonAtlas.createSprite("optionen"))
        } else {
            Image(mainButtonAtlas.createSprite("options"))
        }
        optionsTitle.x = 90f
        optionsTitle.y = 550f


        languageLabel = if (game.currentLang().language == Language.DE) {
            Label("Sprache", game.wanneSkin)
        } else {
            Label("Language", game.wanneSkin)
        }
        languageLabel.setPosition(570f, 450f)

        modeLabel = if (game.currentLang().language == Language.DE) {
            Label("Spielmodus", game.wanneSkin)
        } else {
            Label("Game mode", game.wanneSkin)
        }
        modeLabel.setPosition(570f, 250f)

        val backSprite = if (game.currentLang().language == Language.DE) {
            "zuruckspeichern"
        } else {
            "backsave"
        }
        backButton = game.createUIButton(
            mainButtonAtlas.createSprite(backSprite),
            mainButtonAtlas.createSprite(backSprite + "_pressed"),
            320f,
            35f,
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

        stage.addActor(optionsTitle)
        stage.addActor(languageLabel)
        //stage.addActor(modeLabel)
        stage.addActor(backButton)
    }

    private fun parsePercent(value: Float): Int {
        return value.times(10).toInt().times(10)
    }

    private fun createLangButtons() {
        gerButton = game.createUIButton(
            mainButtonAtlas.createSprite("gerButton"+getButtonSelectionState(Speech.DE_NEU)),
            mainButtonAtlas.createSprite("gerButton_pressed"),
            560f,
            350F,
        )
        gerButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.DE_NEU.speech
                    updateLanguageSensitiveObjects()
                }
            },
        )

        engButton = game.createUIButton(
            mainButtonAtlas.createSprite("engButton"+getButtonSelectionState(Speech.EN)),
            mainButtonAtlas.createSprite("engButton_pressed"),
            735f,
            350F,
        )
        engButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.EN.speech
                    updateLanguageSensitiveObjects()
                }
            },
        )

        orgButton = game.createUIButton(
            mainButtonAtlas.createSprite("orgButton"+getButtonSelectionState(Speech.DE_ORIGINAL)),
            mainButtonAtlas.createSprite("orgButton_pressed"),
            910f,
            350F,
        )
        orgButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.DE_ORIGINAL.speech
                    updateLanguageSensitiveObjects()
                }
            },
        )

        droglButton = game.createUIButton(
            mainButtonAtlas.createSprite("droglButton"+getButtonSelectionState(Speech.DROGL)),
            mainButtonAtlas.createSprite("droglButton_pressed"),
            1085f,
            350F,
        )
        droglButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.DROGL.speech
                    updateLanguageSensitiveObjects()
                }
            },
        )

        stage.addActor(gerButton)
        stage.addActor(engButton)
        stage.addActor(orgButton)
        stage.addActor(droglButton)
    }

    private fun getButtonSelectionState(speech: Speech) : String {
        return if(game.currentLang() == speech) {
            "_selected"
        } else {
            ""
        }
    }
}
