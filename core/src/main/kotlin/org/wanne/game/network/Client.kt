package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.Socket
import com.badlogic.gdx.net.SocketHints
import org.wanne.game.Config
import java.io.ObjectOutputStream

class Client(private val config: Config) {
    private lateinit var socket: Socket

    private val hints = SocketHints().apply {
        connectTimeout = 4000
    }

    private lateinit var outputStream: ObjectOutputStream

    fun connect(): Boolean {
        try {
            println("Verbinde mit Server ${config.ipAddress}:${config.clientPort}")
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, config.ipAddress, config.clientPort, hints)
            outputStream = ObjectOutputStream(socket.outputStream)

            return true
        } catch (e: Exception) {
            println(e.message)
        }
        return false
    }

    fun sayHello(): Boolean {
        if (connect()) {
            try {
                val helloPackage = Package()
                outputStream.writeObject(helloPackage)
                outputStream.flush()

                return true
            } catch (e: Exception) {
                println(e.message)
            }
        }
        return false
    }

}
