package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.WanneGame

class MainMenuScreen(var game: WanneGame) : Screen {

    private var logoHeadline: Texture = Texture(Gdx.files.internal("pictures/Menue/header.png"))

    private var duck: Texture = Texture(Gdx.files.internal("pictures/Players/Ente/p2lookLeft0.png"))

    private var poolAttendant: Texture = Texture(Gdx.files.internal("pictures/Players/Bademeister/p1lookRight0.png"))

    private var menuStage: Stage? = null

    override fun show() {
        // Fenster Größe setzen
        Gdx.graphics.setWindowedMode(400, 250)

        menuStage = Stage()
        Gdx.input.inputProcessor = menuStage

        // Singleplayer Button
        val skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        val startButton = TextButton("Singleplayer", skin, "default")
        startButton.setPosition(250f, 250f)
        startButton.setSize(210f, 60f)

        startButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Button Pressed")
                    game.screen = RoomScreen()
                    dispose()
                }
            },
        )

        menuStage!!.addActor(startButton)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        menuStage!!.act()
        menuStage!!.draw()

        game.batch!!.begin()
        game.batch!!.draw(logoHeadline, 100f, 180f)
        game.batch!!.draw(duck, 300f, 50f)
        game.batch!!.draw(poolAttendant, -30f, -70f)
        game.batch!!.end()

//        game.font!!.draw(game.batch, "Welcome to Drop!!! ", 100f, 150f)

//        if (Gdx.input.isTouched) {
//            game.screen = GameScreenKotlin(game)
//            dispose()
//        }
    }

    override fun resize(
        width: Int,
        height: Int,
    ) {
        // Not needed atm
    }

    override fun pause() {
        // Not needed atm
    }

    override fun resume() {
        // Not needed atm
    }

    override fun hide() {
        // Not needed atm
    }

    override fun dispose() {
        menuStage = null
    }
}
