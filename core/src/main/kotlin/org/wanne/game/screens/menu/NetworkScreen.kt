package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import org.wanne.game.Language
import org.wanne.game.WanneGame
import org.wanne.game.model.Point
import org.wanne.game.screens.util.UiButtonBuilder
import java.net.NetworkInterface

class NetworkScreen(
    game: WanneGame,
) : AbstractOptionsScreen(game) {

    override fun buildMenu() {
        // Server starten
        game.network.startServer()

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
                    connectButton.isDisabled = true
                    connectButton.setText("Verbinde...")
                    if (game.network.startClient(ipField.text, portField.text)) {
                        connectButton.setText("Verbunden")
                    } else {
                        game.network.stopItAll()
                        connectButton.setText("Verbinden")
                        connectButton.isDisabled = false
                    }
                }
            }
        )

        val backSprite = if (game.currentLang().language == Language.EN) {
            "back"
        } else {
            "zuruck"
        }
        val backButton = UiButtonBuilder()
            .withTexture(mainButtonAtlas.createSprite(backSprite))
            .withTexturePressed(mainButtonAtlas.createSprite(backSprite + "_pressed"))
            .withPoint(Point(500F, 80F))
            .build()
        backButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    game.network.stopItAll()
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
