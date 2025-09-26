package org.wanne.game.network

import org.wanne.game.WanneGame

class NetworkManager(val game: WanneGame) {

    lateinit var client: Client
    lateinit var server: Server
    lateinit var serverThread: Thread

    private val ipRegex = Regex("((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}")
    private val integerChars = '0'..'9'

    fun startServer() {
        server = Server(game.config)
        serverThread = Thread( server, "Server" ).also { it.start() }
    }

    fun startClient(ip: String, port: String): Boolean {
        if (checkAndSaveIPAndPort(ip, port)) {
            // Client erstellen und verbinden
            client = Client(game.config)
            return client.sayHello()
        } else {
            println("IP oder Port nicht valide")
        }
        return false
    }

    fun stopItAll() {
        println("Server shutdown")
        server.stop()
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

}
