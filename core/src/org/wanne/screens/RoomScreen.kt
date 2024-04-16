package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.model.Duck
import org.wanne.model.Player
import org.wanne.model.PoolAttendant
import kotlin.system.exitProcess

class RoomScreen(var game: WanneGame) : Screen {
    private var stage: Stage? = null

    private var batch: SpriteBatch? = null

    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Kinderzimmer.png"))

    // Buttons
    private val buttonAtlas: TextureAtlas = TextureAtlas("pictures/Buttons/buttons.atlas")

    // Players
    private var poolAttendant: Player = PoolAttendant(200F, 200F, Player.Companion.Looking.RIGHT)
    private var duck: Player = Duck(600F, 200F, Player.Companion.Looking.LEFT)

    var stateTime: Float = 0f

    private var viewport: FitViewport? = null

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = SpriteBatch()
        stage = Stage(viewport)
        Gdx.input.inputProcessor = stage

        createGameUI()
    }

    private fun createGameUI() {
        // Buttons
        // Nur beim Single-Player sind die Buttons notwendig
        if (game.isSingleplayer) {
            val poolAttendantButton =
                createUIButton(
                    buttonAtlas.createSprite("Bademeister"),
                    buttonAtlas.createSprite("BademeisterPressed"),
                    5f,
                    705f,
                )
            poolAttendantButton.addListener(
                object : ChangeListener() {
                    override fun changed(
                        event: ChangeEvent?,
                        actor: Actor?,
                    ) {
                        println("Switch to Pool Attendant")
                    }
                },
            )
            poolAttendantButton.isVisible = false

            val duckButton =
                createUIButton(
                    buttonAtlas.createSprite("Ente"),
                    buttonAtlas.createSprite("EntePressed"),
                    5f,
                    705f,
                )
            duckButton.addListener(
                object : ChangeListener() {
                    override fun changed(
                        event: ChangeEvent?,
                        actor: Actor?,
                    ) {
                        println("Switch to Duck")
                    }
                },
            )

            stage!!.addActor(poolAttendantButton)
            stage!!.addActor(duckButton)
        }

        val lookButton =
            createUIButton(
                buttonAtlas.createSprite("Ansehen"),
                buttonAtlas.createSprite("AnsehenPressed"),
                5f,
                45f,
            )
        lookButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Look at stuff")
                }
            },
        )

        val speakButton =
            createUIButton(
                buttonAtlas.createSprite("Reden"),
                buttonAtlas.createSprite("RedenPressed"),
                65f,
                45f,
            )
        speakButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Speak to stuff")
                }
            },
        )

        val takeButton =
            createUIButton(
                buttonAtlas.createSprite("Nehmen"),
                buttonAtlas.createSprite("NehmenPressed"),
                120f,
                45f,
            )
        takeButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Take stuff")
                }
            },
        )

        val useButton =
            createUIButton(
                buttonAtlas.createSprite("Benutzen"),
                buttonAtlas.createSprite("BenutzenPressed"),
                180f,
                45f,
            )
        useButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Use stuff")
                }
            },
        )

        val combineButton =
            createUIButton(
                buttonAtlas.createSprite("kombinieren"),
                buttonAtlas.createSprite("kombinierenPressed"),
                240f,
                40f,
            )
        combineButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Combine stuff")
                }
            },
        )

        val exitButton =
            createUIButton(
                buttonAtlas.createSprite("exit"),
                buttonAtlas.createSprite("exitPressed"),
                980f,
                705f,
            )
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

        stage!!.addActor(lookButton)
        stage!!.addActor(speakButton)
        stage!!.addActor(takeButton)
        stage!!.addActor(useButton)
        stage!!.addActor(combineButton)
        stage!!.addActor(exitButton)
    }

    private fun createUIButton(
        texture: Sprite,
        texturePressed: Sprite,
        x: Float,
        y: Float,
    ): ImageButton {
        val style = ImageButtonStyle()
        style.imageUp = TextureRegionDrawable(texture)
        style.imageDown = TextureRegionDrawable(texturePressed)
        val button = ImageButton(style)
        button.x = x
        button.y = y

        return button
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport!!.apply()

        // Animationen holen
        stateTime += Gdx.graphics.deltaTime
        val poolAttendantSprite: Sprite = poolAttendant.getSpriteOfCurrentState(stateTime)
        val duckSprite: Sprite = duck.getSpriteOfCurrentState(stateTime)

        // Zeichnen
        batch!!.projectionMatrix = viewport!!.camera.combined
        batch!!.begin()

        // Background
        if (game.isSingleplayer) {
            batch!!.draw(roomBackgroundSingle, 0f, 0f)
        } else {
            batch!!.draw(roomBackgroundMulti, 0f, 0f)
        }

        // Players
        poolAttendantSprite.draw(batch)
        duckSprite.draw(batch)
        batch!!.end()

        stage!!.act()
        stage!!.draw()
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
        viewport!!.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch!!.dispose()
        buttonAtlas.dispose()
        poolAttendant.dispose()
        duck.dispose()
    }
}
