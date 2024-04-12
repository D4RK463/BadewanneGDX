package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import kotlin.system.exitProcess

class RoomScreen(var game: WanneGame) : Screen {
    private var stage: Stage? = null

    private var batch: SpriteBatch? = null

    // Background
    private val roomBackgroundSingle: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/KinderzimmerSingle.png"))
    private val roomBackgroundMulti: Texture = Texture(Gdx.files.internal("pictures/Backgrounds/Kinderzimmer.png"))

    // Buttons
    private val lookButtonNormal: Texture = Texture(Gdx.files.internal("pictures/Buttons/Ansehen.png"))
    private val lookButtonPressed: Texture = Texture(Gdx.files.internal("pictures/Buttons/AnsehenPressed.png"))
    private val speakButtonNormal: Texture = Texture(Gdx.files.internal("pictures/Buttons/Reden.png"))
    private val speakButtonPressed: Texture = Texture(Gdx.files.internal("pictures/Buttons/RedenPressed.png"))
    private val takeButtonNormal: Texture = Texture(Gdx.files.internal("pictures/Buttons/Nehmen.png"))
    private val takeButtonPressed: Texture = Texture(Gdx.files.internal("pictures/Buttons/NehmenPressed.png"))
    private val useButtonNormal: Texture = Texture(Gdx.files.internal("pictures/Buttons/Benutzen.png"))
    private val useButtonPressed: Texture = Texture(Gdx.files.internal("pictures/Buttons/BenutzenPressed.png"))
    private val combineButtonNormal: Texture = Texture(Gdx.files.internal("pictures/Buttons/kombinieren.png"))
    private val combineButtonPressed: Texture = Texture(Gdx.files.internal("pictures/Buttons/kombinierenPressed.png"))
    private val exitButtonNormal: Texture = Texture(Gdx.files.internal("pictures/Buttons/exit.png"))
    private val exitButtonPressed: Texture = Texture(Gdx.files.internal("pictures/Buttons/exitPressed.png"))

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
        val lookButton = createUIButton(lookButtonNormal, lookButtonPressed, 5f, 45f)
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

        val speakButton = createUIButton(speakButtonNormal, speakButtonPressed, 65f, 45f)
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

        val takeButton = createUIButton(takeButtonNormal, takeButtonPressed, 120f, 45f)
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

        val useButton = createUIButton(useButtonNormal, useButtonPressed, 180f, 45f)
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

        val combineButton = createUIButton(combineButtonNormal, combineButtonPressed, 240f, 40f)
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

        val exitButton = createUIButton(exitButtonNormal, exitButtonPressed, 980f, 705f)
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
        texture: Texture,
        texturePressed: Texture,
        x: Float,
        y: Float,
    ): ImageButton {
        val style = ImageButtonStyle()
        style.imageUp = getDrawableOfTexture(texture)
        style.imageDown = getDrawableOfTexture(texturePressed)
        val button = ImageButton(style)
        button.x = x
        button.y = y

        return button
    }

    private fun getDrawableOfTexture(texture: Texture): TextureRegionDrawable {
        val textureRegion = TextureRegion(texture)
        return TextureRegionDrawable(textureRegion)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        viewport!!.apply()

        // Background
        batch!!.projectionMatrix = viewport!!.camera.combined
        batch!!.begin()

        if (game.isSingleplayer) {
            batch!!.draw(roomBackgroundSingle, 0f, 0f)
        } else {
            batch!!.draw(roomBackgroundMulti, 0f, 0f)
        }
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
    }
}
