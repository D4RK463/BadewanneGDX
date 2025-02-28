package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.Language
import org.wanne.game.screens.util.UiButtonBuilder
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.model.animation.WaterAnimation
import org.wanne.game.model.player.Duck
import org.wanne.game.model.player.Player
import org.wanne.game.model.player.PoolAttendant
import org.wanne.game.stage.MainMenuStage
import kotlin.system.exitProcess

class MainMenuScreen(
    game: WanneGame,
) : AbstractMenuScreen(game) {
    private var logoHeadline: Image = Image(game.am.get("pictures/Menue/title3.png") as Texture)
    private var edge: Image = Image(game.am.get("pictures/Menue/ecke.png") as Texture)

    private var background: Texture = game.am.get("pictures/Menue/background.png")

    private val duck = Duck(Point(485F, 90F), Player.Companion.Looking.RIGHT, am = game.am)
    private val poolAttendant = PoolAttendant(Point(700F, 95F), Player.Companion.Looking.RIGHT, am = game.am)

    private val buttonAtlas: TextureAtlas = game.am.get("pictures/Buttons/buttons.atlas")

    private val waterAnimation = WaterAnimation(938F, 170F, true, game.am)

    // Ambience Musik
    private val musicBackground: Music = game.am.get("soundsOriginal/Background/title_theme.mp3")

    init {
        duck.scaleX *= 1.2F
        duck.scaleY *= 1.2F

        poolAttendant.scaleX *= 1.4F
        poolAttendant.scaleY *= 1.4F
    }

    override fun show() {
        Gdx.graphics.setWindowedMode(1280, 720)
        viewport = FitViewport(1280f, 720f)

        stage = MainMenuStage(viewport, poolAttendant, duck, listOf(waterAnimation))

        logoHeadline.x = 100f
        logoHeadline.y = 550f

        edge.x = 1158F
        edge.y = 573F

        stage.addActor(Image(background))
        stage.addActor(logoHeadline)

        musicBackground.volume = game.config.musicVolume
        musicBackground.isLooping = true
        musicBackground.play()

        if (!game.android) {
            stage.addActor(edge)
        }

        Gdx.input.inputProcessor = stage

        buildMenu()
    }

    private fun buildMenu() {
        val singlePlayerSprite = if (game.startedGame) {
            if (game.currentLang().language == Language.EN) {
                "continue"
            } else {
                "weiter"
            }
        } else {
            "singleplayer"
        }

        val startButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(singlePlayerSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(singlePlayerSprite+"_pressed"))
            .withPoint(Point(100F, 320F))
            .build()
        startButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {

                    if (game.gameEnded) {
                        musicBackground.stop()
                        game.screen = game.introVideoScreen
                    } else {
                        if (game.arrivedOutside) {
                            musicBackground.stop()
                            game.screen = game.outsideScreen
                        } else {
                            musicBackground.stop()
                            game.screen = game.roomScreen
                        }
                    }
                    dispose()
                }
            },
        )

        val multiButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite("multiplayer"))
            .withTexturePressed(mainButtonAtlas.createSprite("multiplayer_pressed"))
            .withPoint(Point(100F, 250F))
            .build()
        multiButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.networkScreen
                    dispose()
                }
            },
        )
        multiButton.isDisabled = true

        val optionsSprite = if (game.currentLang().language == Language.DE) {
            "optionen"
        } else {
            "options"
        }
        val optionsButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(optionsSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(optionsSprite+"_pressed"))
            .withPoint(Point(100F, 180F))
            .build()
        optionsButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.optionsScreen
                    dispose()
                }
            },
        )

//        val extrasButton = UiButtonBuilder()
//            .withTexture(mainButtonAtlas.createSprite("extras"))
//            .withTexturePressed(mainButtonAtlas.createSprite("extras_pressed"))
//            .withPoint(Point(100F, 110F))
//            .build()
//        extrasButton.addListener(
//            object : ChangeListener() {
//                override fun changed(
//                    event: ChangeEvent?,
//                    actor: Actor?,
//                ) {
//                    game.screen = game.optionsScreen
//                    dispose()
//                }
//            },
//        )
//        extrasButton.isDisabled = true

        val exitButton = UiButtonBuilder()
            .withTexture(buttonAtlas.createSprite("exit"))
            .withTexturePressed(buttonAtlas.createSprite("exitPressed"))
            .withPoint(Point(1225F, 645F))
            .useScaling(true)
            .build()
        exitButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    exitProcess(0)
                }
            },
        )

        stage.addActor(startButton)
        stage.addActor(multiButton)
        stage.addActor(optionsButton)
//        stage.addActor(extrasButton)

        if (!game.android) {
            stage.addActor(exitButton)
        }
    }

    override fun dispose() {
        stage.dispose()
    }
}
