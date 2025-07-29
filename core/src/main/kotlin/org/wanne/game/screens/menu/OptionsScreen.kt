package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.screens.util.UiButtonBuilder
import org.wanne.game.VideoMode
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.sound.Speech

class OptionsScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    private val musicVolumeLabelText = "Music: "
    private val soundVolumeLabelText = "Sound: "

    private lateinit var optionsTitle: Image
    private lateinit var languageLabel: Label
    private lateinit var languageLabelShadow: Shadow
    private lateinit var modeLabel: Label
    private lateinit var modeLabelShadow: Shadow
    private lateinit var backButton: ImageButton

    private lateinit var gerButton: ImageButton
    private lateinit var engButton: ImageButton
    private lateinit var orgButton: ImageButton
    private lateinit var droglButton: ImageButton

    private lateinit var classicButton: ImageButton
    private lateinit var modernButton: ImageButton

    override fun buildMenu() {
        val soundVolumePair = createLabelWithShadow("${soundVolumeLabelText}${parsePercent(game.config.soundVolume)}%", 100f, 450f)
        val soundVolumeLabel = soundVolumePair.first
        val soundVolumeLabelShadow = soundVolumePair.second

        val soundSlider = Slider(0F, 1F, 0.05F, false, game.wanneSkin)
        soundSlider.setPosition(100f, 340f)
        soundSlider.width = 390F
        soundSlider.value = game.config.soundVolume
        soundSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.soundVolume = soundSlider.value

                val newVolumeString = "${soundVolumeLabelText}${parsePercent(soundSlider.value)}%"
                soundVolumeLabel.setText("")
                soundVolumeLabel.setText(newVolumeString)
                soundVolumeLabelShadow.setText("")
                soundVolumeLabelShadow.setText(newVolumeString)
            }
        })

        val musicVolumePair = createLabelWithShadow("${musicVolumeLabelText}${parsePercent(game.config.musicVolume)}%", 100f, 250f)
        val musicVolumeLabel = musicVolumePair.first
        val musicVolumeLabelShadow = musicVolumePair.second

        val musicSlider = Slider(0F, 1F, 0.05F, false, game.wanneSkin)
        musicSlider.setPosition(100f, 140F)
        musicSlider.width = 390F
        musicSlider.value = game.config.musicVolume
        musicSlider.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                game.config.musicVolume = musicSlider.value

                val newVolumeString = "${musicVolumeLabelText}${parsePercent(musicSlider.value)}%"
                musicVolumeLabel.setText("")
                musicVolumeLabel.setText(newVolumeString)
                musicVolumeLabelShadow.setText("")
                musicVolumeLabelShadow.setText(newVolumeString)
            }
        })

        stage.addActor(soundVolumeLabelShadow)
        stage.addActor(soundVolumeLabel)
        stage.addActor(soundSlider)
        stage.addActor(musicVolumeLabelShadow)
        stage.addActor(musicVolumeLabel)
        stage.addActor(musicSlider)

        createLanguageSensitiveObjects()
        createLangButtons()
        createModeButtons()
    }

    private fun updateObjects() {
        optionsTitle.remove()
        languageLabel.remove()
        languageLabelShadow.remove()
        modeLabel.remove()
        modeLabelShadow.remove()
        backButton.remove()

        gerButton.remove()
        engButton.remove()
        orgButton.remove()

        if (game.config.drooglUnlocked) {
            droglButton.remove()
        }

        classicButton.remove()
        modernButton.remove()

        createLanguageSensitiveObjects()
        createLangButtons()
        createModeButtons()
    }

    private fun createLanguageSensitiveObjects() {
        optionsTitle = if (game.currentLang().language == Language.DE) {
            Image(mainButtonAtlas.createSprite("optionen"))
        } else {
            Image(mainButtonAtlas.createSprite("options"))
        }
        optionsTitle.x = 90f
        optionsTitle.y = 550f

        val languageLabelPair = createLabelWithShadow(game.choose("Sprache", "Language", false), 570f, 450f)
        languageLabel = languageLabelPair.first
        languageLabelShadow = languageLabelPair.second

        val modelLabelPair = createLabelWithShadow(game.choose("Spielmodus", "Game mode", false), 570f, 250f)
        modeLabel = modelLabelPair.first
        modeLabelShadow = modelLabelPair.second

        val backSprite = game.choose("zuruckspeichern", "backsave", false)
        backButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(backSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(backSprite + "_pressed"))
            .withPoint(Point(320F, 35F))
            .build()
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
        stage.addActor(languageLabelShadow)
        stage.addActor(languageLabel)
        stage.addActor(modeLabelShadow)
        stage.addActor(modeLabel)
        stage.addActor(backButton)
    }

    private fun parsePercent(value: Float): Int {
        return value.times(10).toInt().times(10)
    }

    private fun createLangButtons() {
        gerButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite("gerButton"+getButtonSelectionState(Speech.DE_NEU)))
            .withTexturePressed(mainButtonAtlas.createSprite("gerButton_pressed"))
            .withPoint(Point(560F, 350F))
            .build()
        gerButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.DE_NEU.speech
                    updateObjects()
                }
            },
        )

        engButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite("engButton"+getButtonSelectionState(Speech.EN)))
            .withTexturePressed(mainButtonAtlas.createSprite("engButton_pressed"))
            .withPoint(Point(735F, 350F))
            .build()
        engButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.EN.speech
                    updateObjects()
                }
            },
        )

        orgButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite("orgButton"+getButtonSelectionState(Speech.DE_ORIGINAL)))
            .withTexturePressed(mainButtonAtlas.createSprite("orgButton_pressed"))
            .withPoint(Point(910F, 350F))
            .build()
        orgButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.speech = Speech.DE_ORIGINAL.speech
                    updateObjects()
                }
            },
        )

        if (game.config.drooglUnlocked) {
            droglButton = UiButtonBuilder()
                .withTexture(mainButtonAtlas.createSprite("droglButton"+getButtonSelectionState(Speech.DROGL)))
                .withTexturePressed(mainButtonAtlas.createSprite("droglButton_pressed"))
                .withPoint(Point(1085F, 350F))
                .build()
            droglButton.addListener(
                object : ChangeListener() {
                    override fun changed(
                        event: ChangeEvent?,
                        actor: Actor?,
                    ) {
                        game.config.speech = Speech.DROGL.speech
                        updateObjects()
                    }
                },
            )

            stage.addActor(droglButton)
        }

        stage.addActor(gerButton)
        stage.addActor(engButton)
        stage.addActor(orgButton)
    }

    private fun createModeButtons() {
        classicButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite("classicButton" + getButtonSelectionState(VideoMode.CLASSIC)))
            .withTexturePressed(mainButtonAtlas.createSprite("classicButton_pressed"))
            .withPoint(Point(560F, 149F))
            .build()
        classicButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.mode = VideoMode.CLASSIC.toString()
                    updateObjects()
                }
            },
        )

        modernButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite("modernButton" + getButtonSelectionState(VideoMode.MODERN)))
            .withTexturePressed(mainButtonAtlas.createSprite("modernButton_pressed"))
            .withPoint(Point(735F, 150F))
            .build()
        modernButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.config.mode = VideoMode.MODERN.toString()
                    updateObjects()
                }
            },
        )

        stage.addActor(classicButton)
        stage.addActor(modernButton)
    }

    private fun getButtonSelectionState(speech: Speech) : String {
        return if(game.currentLang() == speech) {
            "_selected"
        } else {
            ""
        }
    }

    private fun getButtonSelectionState(mode: VideoMode) : String {
        return if(game.config.mode == mode.toString()) {
            "_selected"
        } else {
            ""
        }
    }
}
