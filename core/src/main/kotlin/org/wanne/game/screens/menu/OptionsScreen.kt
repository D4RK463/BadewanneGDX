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

    lateinit var optionsTitle: Image
    lateinit var languageLabel: Label
    lateinit var modeLabel: Label
    lateinit var backButton: ImageButton

    lateinit var gerButton: ImageButton
    lateinit var engButton: ImageButton
    lateinit var orgButton: ImageButton
    lateinit var droglButton: ImageButton

    lateinit var classicButton: ImageButton
    lateinit var modernButton: ImageButton

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

        createLanguageSensitiveObjects()
        createLangButtons()
        createModeButtons()
    }

    private fun updateObjects() {
        optionsTitle.remove()
        languageLabel.remove()
        modeLabel.remove()
        backButton.remove()

        gerButton.remove()
        engButton.remove()
        orgButton.remove()
        droglButton.remove()

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
        stage.addActor(languageLabel)
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

        stage.addActor(gerButton)
        stage.addActor(engButton)
        stage.addActor(orgButton)
        stage.addActor(droglButton)
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
