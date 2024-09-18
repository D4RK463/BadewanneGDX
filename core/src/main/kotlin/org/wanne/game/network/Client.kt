package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.Socket
import com.badlogic.gdx.net.SocketHints
import org.wanne.game.Config
import java.io.InputStream
import java.io.OutputStream

class Client(private val config: Config) {

    companion object {
        private val ipRegex = Regex("((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}")

        fun checkIPAddress(ipAddress: String): Boolean {
            return ipRegex.matches(ipAddress) && ("127.0.0.1" != ipAddress) && ("localhost" != ipAddress)
        }

        private val integerChars = '0'..'9'

        fun checkPort(port: String): Boolean {
            return port != "" && port.all { it in integerChars }
        }
    }

    private lateinit var socket: Socket

    lateinit var inputStream: InputStream
    lateinit var outputStream: OutputStream

    fun connect() {
        try {
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, config.ipAddress, config.clientPort, SocketHints())
        } catch (e: Exception) {
            println(e.message)
        }
    }

}
