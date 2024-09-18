package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.ServerSocket
import com.badlogic.gdx.net.ServerSocketHints
import org.wanne.game.Config

class Server(private val config: Config) {

    private lateinit var serverSocket: ServerSocket

    fun start() {
        try {
            serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, config.serverPort, ServerSocketHints())
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun stop() {
        try {
            serverSocket.dispose()
        } catch (e: Exception) {
            println(e.message)
        }
    }
}
