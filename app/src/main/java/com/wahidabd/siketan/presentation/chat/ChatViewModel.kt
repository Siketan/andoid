package com.wahidabd.siketan.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.siketan.BuildConfig
import com.wahidabd.siketan.data.chat.model.request.ChatJoinRequest
import com.wahidabd.siketan.data.chat.model.request.ChatLatestPetaniRequest
import com.wahidabd.siketan.data.chat.model.request.ChatLatestRequest
import com.wahidabd.siketan.data.chat.model.request.ChatSendRequest
import com.wahidabd.siketan.data.chat.model.response.ChatMessageResponse
import com.wahidabd.siketan.data.chat.model.response.ChatPetaniResponse
import com.wahidabd.siketan.data.chat.model.response.ChatResponse
import com.wahidabd.siketan.domain.chat.ChatUseCase
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.json.JSONObject


/**
 * Created by Wahid on 8/6/2023.
 * Github github.com/wahidabd.
 */


class ChatViewModel(private val useCase: ChatUseCase) : ViewModel() {

    private val _getUserChat = MutableLiveData<Resource<ChatPetaniResponse>>()
    val getUserChat: LiveData<Resource<ChatPetaniResponse>> get() = _getUserChat

    private val _getLatestMessages = MutableLiveData<Resource<ChatResponse>>()
    val getLatestMessages: LiveData<Resource<ChatResponse>> get() = _getLatestMessages

    private val _newMessage = MutableLiveData<ChatMessageResponse>()

    private val _join = MutableLiveData<Boolean>()
    val join: LiveData<Boolean> get() = _join

    private var socket: Socket
    private var onOnline: Emitter.Listener
    private var onReceived: Emitter.Listener

    init {
        val options = IO.Options()
        options.transports = arrayOf("websocket")
        options.upgrade = false

        socket = IO.socket("http://192.168.100.50:3001", options)
//        socket = IO.socket(BuildConfig.BASE_URL)

        onOnline = Emitter.Listener { args ->
            viewModelScope.launch {
                val obj = args[0] as JSONObject
                val status = obj.getString("status")
                _join.value = status != "offline"
            }
        }

        onReceived = Emitter.Listener { args ->
            viewModelScope.launch {
                val obj = args[0] as JSONObject
                debug { "chat room: $obj" }
            }
        }
    }

    fun onConnect() {
        socket.connect()
        socket.on("online", onOnline)
        socket.on("received", onReceived)
    }

    fun sendMessage(data: ChatSendRequest) {
        socket.emit("message", data.toObj())
    }

    fun onJoin(data: ChatJoinRequest) {
        socket.emit("join", data.toObj())
    }

    fun getLatestMessages(data: ChatLatestRequest) {
        useCase.getLatestChat(data)
            .onEach { _getLatestMessages.value = it }
            .launchIn(viewModelScope)
    }

    fun getPetaniChat(id: Int) {
        useCase.getPetaniChat(id)
            .onEach { _getUserChat.value = it }
            .launchIn(viewModelScope)
    }

    fun getLatestChatPetani(data: ChatLatestPetaniRequest){
        useCase.getLatestChatPetani(data)
            .onEach { _getLatestMessages.value = it }
            .launchIn(viewModelScope)
    }

}