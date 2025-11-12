package org.wanne.game.screens.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
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
        val multiTitle = Image(mainButtonAtlas.createSprite("multiplayer"))
        multiTitle.x = 90f
        multiTitle.y = 550f

        // Server, also wenn ich selbst hoste
        val serverIpLabelPair = createLabelWithShadow("Server IP-Adresse: ${getIpAddress()}", 100f, 480f)
        val serverIpLabel = serverIpLabelPair.first
        val serverIpLabelShadow = serverIpLabelPair.second

        val serverPortLabelPair = createLabelWithShadow("Port:", 800f, 480f)
        val serverPortLabel = serverPortLabelPair.first
        val serverPortLabelShadow = serverPortLabelPair.second

        val serverPortField = TextField("", skin)
        serverPortField.x = 920f
        serverPortField.y = 480f
        serverPortField.width = 50f
        serverPortField.height = 50f
        serverPortField.text = game.config.serverPort.toString()

        val serverButton = createTextButton("Server starten", 100f, 380f)

        // Client, also wenn ich mich mit einem anderen verbinde
        val clientIpLabelPair = createLabelWithShadow("Ziel-Server IP-Adresse:", 100f, 280f)
        val clientIpLabel = clientIpLabelPair.first
        val clientIpLabelShadow = clientIpLabelPair.second

        val clientPortLabelPair = createLabelWithShadow("Port:", 800f, 280f)
        val clientPortLabel = clientPortLabelPair.first
        val clientPortLabelShadow = clientPortLabelPair.second

        val clientIpField = TextField("", skin)
        clientIpField.x = 580f
        clientIpField.y = 280f
        clientIpField.width = 160f
        clientIpField.height = 50f
        clientIpField.text = game.config.ipAddress

        val clientPortField = TextField("", skin)
        clientPortField.x = 920f
        clientPortField.y = 280f
        clientPortField.width = 50f
        clientPortField.height = 50f
        clientPortField.text = game.config.clientPort.toString()

        val connectButton = createTextButton("Verbinden", 100f, 180f)
        connectButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    connectButton.isDisabled = true
                    connectButton.setText("Verbinde...")
                    if (game.network.startClient(clientIpField.text, clientPortField.text)) {
                        connectButton.setText("Verbunden")
                        serverButton.isDisabled = true
                    } else {
                        connectButton.setText("Verbinden")
                        connectButton.isDisabled = false
                        serverButton.isDisabled = false
                    }
                }
            }
        )

        serverButton.addListener(
            object : ChangeListener() {
                override fun changed(
                    event: ChangeEvent?,
                    actor: Actor?,
                ) {
                    if (game.network.serverRunning()) {
                        game.network.stopItAll()
                        serverButton.setText("Server starten")
                        connectButton.isDisabled = false
                    } else {
                        serverButton.setText("Starte...")
                        if (game.network.startServer(serverPortField.text)) {
                            serverButton.setText("Stoppen")
                            connectButton.isDisabled = true
                        } else {
                            serverButton.setText("Server starten")
                            connectButton.isDisabled = false
                        }
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
            .withPoint(Point(500F, 35F))
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

        stage.addActor(clientIpLabelShadow)
        stage.addActor(clientIpLabel)
        stage.addActor(serverIpLabelShadow)
        stage.addActor(serverIpLabel)
        stage.addActor(clientPortLabelShadow)
        stage.addActor(clientPortLabel)
        stage.addActor(serverPortLabelShadow)
        stage.addActor(serverPortLabel)
        stage.addActor(serverIpLabel)
        stage.addActor(clientIpField)
        stage.addActor(serverPortField)
        stage.addActor(clientPortField)
        stage.addActor(connectButton)
        stage.addActor(serverButton)
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
