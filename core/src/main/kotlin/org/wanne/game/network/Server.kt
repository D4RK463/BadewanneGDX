package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.ServerSocket
import com.badlogic.gdx.net.ServerSocketHints
import org.wanne.game.Config
import java.io.ObjectInputStream

class Server(private val config: Config): Runnable {

    private lateinit var serverSocket: ServerSocket

    private var running = true

    private val hints = ServerSocketHints().apply {
        acceptTimeout = 2000
    }

    private fun openSocket() {
        try {
            println("Starte Server auf Port ${config.serverPort}")
            serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, config.serverPort, hints)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun stop() {
        try {
            serverSocket.dispose()
            running = false
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun run() {
        openSocket()
        while (running) {
            try {
                val socket = serverSocket.accept(null)
                println("Client connected: ${socket.remoteAddress}")

                val incomingPackage = ObjectInputStream(socket.inputStream).readObject() as Package

                if (incomingPackage.intent == Intent.HELLO) {
                    println("Client sagt Hallo")

                    // ToDo: Server sollte darauf aufmerksam machen, dass ein Client verbunden ist und dann ein Spiel starten
                }

            } catch (e: Exception) {
                println("Warte auf Client... ${e.message}")
            }
        }
        stop()
    }
}
