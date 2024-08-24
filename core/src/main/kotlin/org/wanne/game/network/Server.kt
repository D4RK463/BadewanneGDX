package org.wanne.game.network

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.ServerSocket
import com.badlogic.gdx.net.ServerSocketHints

class Server(private val port: Int) {

    private lateinit var serverSocket: ServerSocket

    fun start() {
        try {
            serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, port, ServerSocketHints());
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
