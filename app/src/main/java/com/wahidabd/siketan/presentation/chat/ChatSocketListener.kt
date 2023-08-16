package com.wahidabd.siketan.presentation.chat

import com.wahidabd.library.utils.extensions.debug
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


/**
 * Created by Wahid on 8/6/2023.
 * Github github.com/wahidabd.
 */


class ChatSocketListener(
    private val viewModel: ChatViewModel
): WebSocketListener() {

//    override fun onOpen(webSocket: WebSocket, response: Response) {
//        super.onOpen(webSocket, response)
//        viewModel.setStatus(true)
//        webSocket.send("Android connected")
//        debug { "Socket: onOpen" }
//    }
//
//    override fun onMessage(webSocket: WebSocket, text: String) {
//        super.onMessage(webSocket, text)
//        viewModel.addMessage(Pair(false, text))
//        debug { "Socket: onMessage" }
//    }
//
//    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//        super.onClosing(webSocket, code, reason)
//        debug { "Socket: onClosing" }
//    }
//
//    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
//        super.onClosed(webSocket, code, reason)
//        viewModel.setStatus(false)
//        debug { "Socket: onClosed" }
//    }
//
//    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//        debug { "Socket: ${t.message} $response" }
//        super.onFailure(webSocket, t, response)
//    }

}