package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.WanneGame
import kotlin.system.exitProcess

class MenuScreen(var game: WanneGame) : Screen {
    private var stage: Stage? = null
    private var skin: Skin? = null

    private var batch: SpriteBatch? = null

    private var logoHeadline: Texture = Texture(Gdx.files.internal("pictures/Menue/header.png"))

    private var duck: Texture = Texture(Gdx.files.internal("pictures/Players/Ente/p2lookLeft0.png"))

    private var poolAttendant: Texture = Texture(Gdx.files.internal("pictures/Players/Bademeister/p1lookRight0.png"))

    override fun show() {
        batch = SpriteBatch()
        stage = Stage()

        Gdx.input.inputProcessor = stage
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        buildMenu()
    }

    override fun hide() {
        stage = null
        skin = null
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(84 / 255f, 88 / 255f, 92 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage!!.act()
        stage!!.draw()

        batch!!.begin()
        batch!!.draw(logoHeadline, 220f, 400f)
        batch!!.draw(duck, 450f, 20f)
        batch!!.draw(poolAttendant, 10f, 20f)
        batch!!.end()
    }

    private fun buildMenu() {
        val startButton = createTextButton("Singleplayer", 220f, 300f)
        startButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = RoomScreen(game)
                    dispose()
                }
            },
        )

        val multiButton = createTextButton("Multiplayer", 220f, 200f)
        multiButton.isDisabled = true
        multiButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Not Implemented... yet :)")
                }
            },
        )

        val exitButton = createTextButton("Beenden", 220f, 100f)
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

        stage!!.addActor(startButton)
        stage!!.addActor(multiButton)
        stage!!.addActor(exitButton)
    }

    private fun createTextButton(
        label: String,
        x: Float,
        y: Float,
    ): TextButton {
        val button = TextButton(label, skin, "default")
        button.setPosition(x, y)
        button.setSize(210f, 60f)

        return button
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        batch!!.dispose()
    }
}
