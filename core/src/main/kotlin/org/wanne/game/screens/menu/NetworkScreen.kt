package org.wanne.game.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import org.wanne.game.WanneGame
import org.wanne.game.network.Client
import org.wanne.game.network.Server
import org.wanne.game.screens.AbstractMenuScreen
import java.net.NetworkInterface

class NetworkScreen(
    var game: WanneGame,
) : AbstractMenuScreen(game) {
    private lateinit var stage: Stage

    override fun show() {
        Gdx.graphics.setWindowedMode(1024, 768)
        viewport = FitViewport(1024f, 768f)

        batch = game.batch
        stage = Stage(viewport)

        Gdx.input.inputProcessor = stage

        buildMenu()
    }

    private fun buildMenu() {

        val ipField = TextField("IP Adresse eingeben", skin)
        ipField.x = 350f
        ipField.y = 500f
        ipField.width = 300f
        ipField.height = 50f
        ipField.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                ipField.text = ""
                return true
            }
        })
        ipField.text = game.config.ipAddress

        val portField = TextField("Port eingeben", skin)
        portField.x = 350f
        portField.y = 450f
        portField.width = 300f
        portField.height = 50f
        portField.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                portField.text = ""
                return true
            }
        })
        portField.text = game.config.serverPort.toString()

        val connectButton = createTextButton("Verbinden", 350f, 320f)
        connectButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    if (Client.checkIPAddress(ipField.text) && Client.checkPort(portField.text)) {
                        game.config.ipAddress = ipField.text
                        game.config.clientPort = portField.text.toInt()
                        game.config.saveSettings()

                        game.client = Client(game.config)

                        println("Verbinde...")
                        game.client.connect()
                    } else {
                        println("IP oder Port nicht valide")
                    }
                }
            },
        )

        val stopServerButton = createTextButton("Server stoppen", 350f, 200f)
        val startServerButton = createTextButton("Server starten", 350f, 200f)
        startServerButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    if (Client.checkPort(portField.text)) {
                        game.config.serverPort = portField.text.toInt()
                        game.config.saveSettings()

                        game.server = Server(game.config)

                        println("Starte...")
                        game.server.start()

                        startServerButton.isVisible = false
                        stopServerButton.isVisible = true
                    } else {
                        println("Port nicht valide")
                    }
                }
            },
        )
        stopServerButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    println("Stoppe...")
                    game.server.stop()
                    startServerButton.isVisible = true
                    stopServerButton.isVisible = false
                }
            },
        )
        stopServerButton.isVisible = false

        val backButton = createTextButton("Zurück", 350f, 80f)
        backButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.screen = game.menuScreen
                    dispose()
                }
            },
        )

        val ipLabel = Label("Eigene IP-Adresse: ${getIpAddress()}", skin)
        ipLabel.setPosition(350f,550f)

        stage.addActor(ipLabel)
        stage.addActor(ipField)
        stage.addActor(portField)
        stage.addActor(connectButton)
        stage.addActor(startServerButton)
        stage.addActor(stopServerButton)
        stage.addActor(backButton)
    }

    private fun getIpAddress() =
        NetworkInterface
            .getNetworkInterfaces()
            .toList()
            .flatMap { it.inetAddresses.toList() }
            .firstOrNull { it.isSiteLocalAddress && (it.hostAddress.startsWith("192.") || it.hostAddress.startsWith("10.") ) }
            ?.hostAddress

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(84 / 255f, 88 / 255f, 92 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewport.apply()

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        stage.dispose()
    }

}
