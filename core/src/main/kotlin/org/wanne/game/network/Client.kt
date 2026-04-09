package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.Socket
import com.badlogic.gdx.net.SocketHints
import org.wanne.game.Config
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class Client(private val config: Config) {
    private lateinit var socket: Socket
    private lateinit var outputStream: ObjectOutputStream
    private lateinit var inputStream: ObjectInputStream
    var isConnected = false

    var onPackageReceived: ((Package) -> Unit)? = null
    var onConnectionLost: (() -> Unit)? = null

    private val hints = SocketHints().apply {
        connectTimeout = 4000
    }

    fun connect(): Boolean {
        try {
            println("Verbinde mit Server ${config.ipAddress}:${config.clientPort}")
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, config.ipAddress, config.clientPort, hints)
            outputStream = ObjectOutputStream(socket.outputStream)
            outputStream.flush()
            inputStream = ObjectInputStream(socket.inputStream)

            isConnected = true
            startReceiving()
            return true
        } catch (e: Exception) {
            println(e.message)
        }
        return false
    }

    private fun startReceiving() {
        Thread({
            while (isConnected) {
                try {
                    val pkg = inputStream.readObject() as Package
                    onPackageReceived?.invoke(pkg)
                } catch (e: Exception) {
                    if (isConnected) {
                        println("Empfangsfehler... disconnecting: ${e.message}")
                        disconnect()
                        onConnectionLost?.invoke()
                    }
                }
            }
        }, "Client-Receive").start()
    }

    fun sendPackage(pkg: Package) {
        try {
            outputStream.writeObject(pkg)
            outputStream.flush()
        } catch (e: Exception) {
            println("Sendefehler: $e")
            disconnect()
            onConnectionLost?.invoke()
        }
    }

    fun disconnect() {
        isConnected = false
        socket.dispose()
    }
}
