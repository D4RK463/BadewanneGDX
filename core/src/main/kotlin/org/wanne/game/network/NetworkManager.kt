package org.wanne.game.network

import org.wanne.game.Statistic
import org.wanne.game.VideoMode
import org.wanne.game.WanneGame
import org.wanne.game.network.converter.ActionConverter
import org.wanne.game.network.model.SerializableAction

class NetworkManager(val game: WanneGame) {
    private var client: Client? = null
    private var server: Server? = null

    private val converter = ActionConverter(game)

    private val ipRegex = Regex("((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}")
    private val integerChars = '0'..'9'

    fun startServer(port: String): Boolean {
        if (checkPort(port)) {
            game.config.serverPort = port.toInt()
            game.config.saveSettings()
            server = Server(game.config).apply {
                onPackageReceived = { pkg -> handleIncomingPackage(pkg) }
                onConnectionLost = { resetGame() }
            }
            Thread(server, "Server").start()
            return true
        }
        return false
    }

    fun startClient(ip: String, port: String): Boolean {
        if (checkAndSaveIPAndPort(ip, port)) {
            client = Client(game.config).apply {
                onPackageReceived = { pkg -> handleIncomingPackage(pkg) }
                onConnectionLost = { resetGame() }
            }
            val connected = client!!.connect()

            if (connected) {
                val helloPkg = Package(
                    intent = Intent.HELLO,
                    selectedVideoMode = VideoMode.valueOf(game.config.mode)
                )
                sendPackage(helloPkg)
            }
            return connected
        }
        return false
    }

    private fun checkAndSaveIPAndPort(ip: String, port: String): Boolean {
        if (checkIPAddress(ip) && checkPort(port)) {
            game.config.ipAddress = ip
            game.config.clientPort = port.toInt()
            game.config.saveSettings()
            return true
        }
        return false
    }

    private fun checkIPAddress(ipAddress: String): Boolean {
        return ipRegex.matches(ipAddress) && ("127.0.0.1" != ipAddress) && ("localhost" != ipAddress)
    }

    private fun checkPort(port: String): Boolean {
        return port != "" && port.all { it in integerChars }
    }

    private fun handleIncomingPackage(pkg: Package) {
        when (pkg.intent) {
            Intent.HELLO -> {
                println("Client sagt hallo, Starte Spiel")

                // Starten an Client senden
                val startPkg = Package(
                    intent = Intent.START,
                    selectedVideoMode = VideoMode.valueOf(game.config.mode)
                )
                sendPackage(startPkg)

                // Spiel starten
                com.badlogic.gdx.Gdx.app.postRunnable {
                    game.reset()
                    game.isSingleplayer = false
                    game.player = 1
                    game.screen = game.introVideoScreen
                }
            }
            Intent.START -> {
                println("Spiel starten")

                // Spiel starten
                com.badlogic.gdx.Gdx.app.postRunnable {
                    game.reset()
                    game.config.mode = pkg.selectedVideoMode.name
                    game.isSingleplayer = false
                    game.player = 2
                    game.screen = game.introVideoScreen
                }
            }
            Intent.CLICK -> {
                println("Klick empfangen: ${pkg.clickData}")
                com.badlogic.gdx.Gdx.app.postRunnable {

                    // Spiel aktualisieren
                    if (game.currentStage != null && pkg.clickData != null) {
                        val action = converter.convertToActionWrapper(pkg.clickData)
                        game.currentListener?.externalClick(game.currentStage!!, action)
                    }
                }
            }
        }
    }

    fun sendClick(clickData: SerializableAction) {
        if (server?.running == true || client?.isConnected == true) {
            val pkg = Package(
                intent = Intent.CLICK,
                clickData = clickData,
                selectedVideoMode = VideoMode.valueOf(game.config.mode)
            )
            sendPackage(pkg)
        } else {
            println("Keine Verbindung zum Senden eines Klicks")
        }
    }

    private fun sendPackage(pkg: Package) {
        client?.sendPackage(pkg)
        server?.sendPackage(pkg)
    }

    fun stopItAll() {
        client?.disconnect()
        server?.stop()
    }

    fun serverRunning(): Boolean = server?.running ?: false

    fun resetGame() {
        game.startedGame = false
        game.gameEnded = true
        Statistic.reset()

        game.reset()
        game.isSingleplayer = true
        game.player = 1
        game.screen = game.mainMenuScreen
    }
}
