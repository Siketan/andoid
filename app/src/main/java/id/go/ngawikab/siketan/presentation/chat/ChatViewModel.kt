package id.go.ngawikab.siketan.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
import id.go.ngawikab.siketan.data.chat.model.request.ChatJoinRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestPetaniRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatSendRequest
import id.go.ngawikab.siketan.data.chat.model.response.ChatMessageResponse
import id.go.ngawikab.siketan.data.chat.model.response.ChatPetaniResponse
import id.go.ngawikab.siketan.data.chat.model.response.ChatResponse
import id.go.ngawikab.siketan.domain.chat.ChatUseCase
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
    val newMessage: LiveData<ChatMessageResponse> get() = _newMessage

    private val _join = MutableLiveData<Boolean>()
    val join: LiveData<Boolean> get() = _join

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var socket: Socket
    private var onOnline: Emitter.Listener
    private var onReceived: Emitter.Listener

    init {
        val options = IO.Options()
        options.transports = arrayOf("websocket")
        options.upgrade = false

        socket = IO.socket("http://192.168.1.45:3001", options)
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
                try {
//                    val id = obj.getString("id")
//                val attachmentId = obj.getString("attachmentId")
                    val pesan = obj.getString("pesan")
                    val chatId = obj.getString("chatId")
                    val waktu = obj.getString("waktu")
                    val fromId = obj.getString("fromId")
                    val attachment = obj.getString("attachment")

                    val data = ChatMessageResponse(
//                        id = id.toIntOrNull(),
                        pesan = pesan,
                        chatId = chatId.toIntOrNull(),
                        waktu = waktu,
                        fromId = fromId.toIntOrNull(),
                        attachment = attachment
                    )

                    _newMessage.value = data
                } catch (e: Exception) {
                    val error = obj.getString("message")
                    _errorMessage.value = error
                }

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

        debug { "Send Message: $data" }
    }

    fun onJoin(data: ChatJoinRequest) {
        socket.emit("join", data.toObj())
        debug { "On Join: $data" }
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

    fun getLatestChatPetani(data: ChatLatestPetaniRequest) {
        useCase.getLatestChatPetani(data)
            .onEach { _getLatestMessages.value = it }
            .launchIn(viewModelScope)
    }

}