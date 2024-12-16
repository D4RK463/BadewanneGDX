package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.UiButtonBuilder
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.network.Client
import org.wanne.game.network.Server
import java.net.NetworkInterface

class NetworkScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    override fun buildMenu() {
        val multiTitle = Image(mainButtonAtlas.createSprite("multiplayer"))
        multiTitle.x = 90f
        multiTitle.y = 550f

        val ipField = TextField("IP Adresse eingeben", skin)
        ipField.x = 500f
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
        portField.x = 500f
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

        val connectButton = createTextButton("Verbinden", 500f, 320f)
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

        val stopServerButton = createTextButton("Server stoppen", 500f, 200f)
        val startServerButton = createTextButton("Server starten", 500f, 200f)
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

        val backSprite = if (game.currentLang().language == Language.EN) {
            "back"
        } else {
            "zuruck"
        }
        val backButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(backSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(backSprite + "_pressed"))
            .withPoint(Point(500, 80))
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

        val ipLabel = Label("Eigene IP-Adresse: ${getIpAddress()}", game.wanneSkin)
        ipLabel.setPosition(500f, 550f)

        stage.addActor(ipLabel)
        stage.addActor(ipField)
        stage.addActor(portField)
        stage.addActor(connectButton)
        stage.addActor(startServerButton)
        stage.addActor(stopServerButton)
        stage.addActor(backButton)
        stage.addActor(multiTitle)
    }

    private fun getIpAddress() =
        NetworkInterface
            .getNetworkInterfaces()
            .toList()
            .flatMap { it.inetAddresses.toList() }
            .firstOrNull { it.isSiteLocalAddress && (it.hostAddress.startsWith("192.") || it.hostAddress.startsWith("10.")) }
            ?.hostAddress

}
