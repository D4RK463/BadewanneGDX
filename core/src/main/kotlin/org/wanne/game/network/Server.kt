package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.ServerSocket
import com.badlogic.gdx.net.ServerSocketHints
import com.badlogic.gdx.net.Socket
import org.wanne.game.Config
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class Server(private val config: Config): Runnable {
    private lateinit var serverSocket: ServerSocket
    var running = true
    private var clientSocket: Socket? = null
    private lateinit var outputStream: ObjectOutputStream
    private lateinit var inputStream: ObjectInputStream

    var onPackageReceived: ((Package) -> Unit)? = null

    private val hints = ServerSocketHints().apply {
        acceptTimeout = 2000
    }

    override fun run() {
        openSocket()

        // Einmal auf Verbindung warten
        while (running && clientSocket == null) {
            try {
                clientSocket = serverSocket.accept(null)
                println("Client verbunden: ${clientSocket?.remoteAddress}")

                inputStream = ObjectInputStream(clientSocket!!.inputStream)
                outputStream = ObjectOutputStream(clientSocket!!.outputStream).apply { flush() }

                startReceiving()
            } catch (e: Exception) {
                println("Warte auf Client... ${e.message}")
            }
        }
    }

    private fun startReceiving() {
        while (running) {
            try {
                val pkg = inputStream.readObject() as Package
                onPackageReceived?.invoke(pkg)
            } catch (e: Exception) {
                if (running) {
                    println("Empfangsfehler: ${e.message}")
                }
            }
        }
    }

    fun sendPackage(pkg: Package) {
        try {
            outputStream.writeObject(pkg)
            outputStream.flush()
        } catch (e: Exception) {
            println("Sendefehler: ${e.message}")
        }
    }

    private fun openSocket() {
        serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, config.serverPort, hints)
        println("Server gestartet auf Port ${config.serverPort}")
    }

    fun stop() {
        running = false
        clientSocket?.dispose()
        serverSocket.dispose()
    }
}
