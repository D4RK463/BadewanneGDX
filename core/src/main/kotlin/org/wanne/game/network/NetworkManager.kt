package org.wanne.game.network

import org.wanne.game.VideoMode
import org.wanne.game.WanneGame

class NetworkManager(val game: WanneGame) {
    var client: Client? = null
    var server: Server? = null

    private val ipRegex = Regex("((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}")
    private val integerChars = '0'..'9'

    fun startServer() {
        server = Server(game.config).apply {
            onPackageReceived = { pkg -> handleIncomingPackage(pkg) }
        }
        Thread(server, "Server").start()
    }

    fun startClient(ip: String, port: String): Boolean {
        if (checkAndSaveIPAndPort(ip, port)) {
            client = Client(game.config).apply {
                onPackageReceived = { pkg -> handleIncomingPackage(pkg) }
            }
            return client!!.connect()
        }
        return false
    }

    private fun checkAndSaveIPAndPort(ip: String, port: String): Boolean {
        if (checkIPAddress(ip) && checkPort(port)) {
            game.config.ipAddress = ip
            game.config.clientPort = port.toInt()
            game.config.serverPort = port.toInt()
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
                println("Client sagt hallo")
                 // Spiel starten
            }
            Intent.CLICK -> {
                println("Klick empfangen: ${pkg.clickData}")
                // Spiel aktualisieren
            }
            else -> println("Unbekanntes Paket: ${pkg.intent}")
        }
    }

    fun sendClick(clickData: SerializablePointAndClickAction) {
        val pkg = Package(
            intent = Intent.CLICK,
            clickData = clickData,
            selectedVideoMode = VideoMode.valueOf(game.config.mode)
        )
        client?.sendPackage(pkg)
        server?.sendPackage(pkg)
    }

    fun stopItAll() {
        client?.disconnect()
        server?.stop()
    }
}
