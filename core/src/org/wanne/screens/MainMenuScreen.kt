package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.ScreenUtils
import org.wanne.game.WanneGame

class MainMenuScreen(var game: WanneGame) : Screen {
    private var logoHeadline: Texture = Texture(Gdx.files.internal("pictures/Menue/header.png"))
    private var duck: Texture = Texture(Gdx.files.internal("pictures/Players/Ente/p2lookLeft0.png"))
    private var poolAttendant: Texture = Texture(Gdx.files.internal("pictures/Players/Bademeister/p1lookRight0.png"))

    private val camera: OrthographicCamera = OrthographicCamera()

    private val startButton: TextButton

    private val menuStage: Stage

    init {
        menuStage = Stage()
        camera.setToOrtho(false, 400f, 250f)

        // Fenster Größe setzen
        Gdx.graphics.setWindowedMode(400, 250)

        // Singleplayer Button
        val buttonStyle = TextButtonStyle()
        buttonStyle.font = game.font
        startButton = TextButton("Singleplayer", buttonStyle)
        startButton.setPosition(300f, 250f)
        startButton.setSize(110f, 30f)
        startButton.setZIndex(1000)

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

        menuStage.addActor(startButton)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)

        camera.update()
        game.batch!!.projectionMatrix = camera.combined

        game.batch!!.begin()
        game.batch!!.draw(logoHeadline, 100f, 180f)
        game.batch!!.draw(duck, 300f, 50f)
        game.batch!!.draw(poolAttendant, -30f, -70f)
        game.batch!!.end()

        menuStage.draw()

//        game.font!!.draw(game.batch, "Welcome to Drop!!! ", 100f, 150f)

//        if (Gdx.input.isTouched) {
//            game.screen = GameScreenKotlin(game)
//            dispose()
//        }
    }

    override fun show() {
        // Not needed atm
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
        // Not needed atm
    }
}
