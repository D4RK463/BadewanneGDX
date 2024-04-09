package org.wanne.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.WanneGame

class MenuTestScreen(var game: WanneGame) : Screen {
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
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage!!.act()
        stage!!.draw()

        val halfScreen = Gdx.graphics.width / 2

        game.batch!!.begin()
        game.batch!!.draw(logoHeadline, halfScreen.toFloat(), 400f)
        game.batch!!.draw(duck, 300f, 50f)
        game.batch!!.draw(poolAttendant, -30f, -70f)
        game.batch!!.end()
    }

    private fun buildMenu() {
        val menuTable = Table(skin)
        menuTable.setPosition(0f, 0f)
        menuTable.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
//        menuTable.align(Align.top)

        stage!!.addActor(menuTable)

//        val texture = Texture(Gdx.files.internal("data/ubuntulogo.png"))
//        val image: Image = Image(texture)
//
//        //first option
//        image.addListener(object : ClickListener() {
//            override fun clicked(event: InputEvent?, x: Float, y: Float) {
//                //your action on click
//            }
//        })
//        menuTable.add(image)
//
//        //second option
//        val imageButton: ImageButton = ImageButton(image.getDrawable())
//        imageButton.addListener(object : ClickListener() {
//            override fun clicked(event: InputEvent?, x: Float, y: Float) {
//                //your action on click
//            }
//        })
//        menuTable.add(imageButton)

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
                }
            },
        )

        val multiButton = TextButton("Multiplayer", skin, "default")
        multiButton.setPosition(300f, 300f)
        multiButton.setSize(210f, 60f)

        multiButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Button Pressed")
                }
            },
        )

        menuTable.add(startButton)
        menuTable.add(multiButton)
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
