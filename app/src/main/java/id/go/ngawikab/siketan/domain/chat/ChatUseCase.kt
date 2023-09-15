package id.go.ngawikab.siketan.domain.chat

import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestPetaniRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestRequest
import id.go.ngawikab.siketan.data.chat.model.response.ChatPetaniResponse
import id.go.ngawikab.siketan.data.chat.model.response.ChatResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 8/26/2023.
 * Github github.com/wahidabd.
 */


interface ChatUseCase {
    fun getLatestChat(data: ChatLatestRequest): Flow<Resource<ChatResponse>>
    fun getPetaniChat(id: Int): Flow<Resource<ChatPetaniResponse>>
    fun getLatestChatPetani(data: ChatLatestPetaniRequest): Flow<Resource<ChatResponse>>
}