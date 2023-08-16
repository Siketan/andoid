package com.wahidabd.siketan.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.siketan.BuildConfig
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.launch
import org.json.JSONObject


/**
 * Created by Wahid on 8/6/2023.
 * Github github.com/wahidabd.
 */


class ChatViewModel : ViewModel() {

    private var socket: Socket? = null

    fun connectWebSocket(){
        try {
            val options = IO.Options()
            socket = IO.socket(BuildConfig.BASE_URL, options)

            debug { "ChatViewModel: ${socket?.id()}" }

            socket?.on(Socket.EVENT_CONNECT, onConnect)
            socket?.on(Socket.EVENT_DISCONNECT, onDisconnect)
            socket?.on(Socket.EVENT_ERROR, onConnectError)
            socket?.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)

            socket?.connect()
        }catch (e: Exception){
            debug { "Exception: ${e.printStackTrace()}" }
            e.printStackTrace()
        }
    }

    private val onJoin = Emitter.Listener { args ->

    }

    private val onConnect = Emitter.Listener { args ->
        debug { "OnConnect Socket" }
        debug { "Args: $args" }

        viewModelScope.launch {
            val obj = args[0] as JSONObject
            debug { "Object: $obj" }
        }
    }

    private val onDisconnect = Emitter.Listener { args ->
        debug { "OnDisconnect Socket" }
        debug { "Args: $args" }
    }

    private val onConnectError = Emitter.Listener { args ->
        debug { "OnConnectError Socket" }
        debug { "Args: $args" }
    }

    fun disconnectWebSocket(){
        socket?.disconnect()
    }

    override fun onCleared() {
        super.onCleared()
        disconnectWebSocket()
    }
}