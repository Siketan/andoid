package id.go.ngawikab.siketan.domain.chat

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestPetaniRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestRequest
import id.go.ngawikab.siketan.data.chat.model.response.ChatPetaniResponse
import id.go.ngawikab.siketan.data.chat.model.response.ChatResponse
import id.go.ngawikab.siketan.data.chat.ChatRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 8/26/2023.
 * Github github.com/wahidabd.
 */


class ChatInteractor(private val repository: ChatRepository) : ChatUseCase {
    override fun getLatestChat(data: ChatLatestRequest): Flow<Resource<ChatResponse>> =
        object : InternetBoundResource<ChatResponse, ChatResponse>() {
            override suspend fun createCall(): Flow<Resource<ChatResponse>> {
                return repository.getLatestChat(data)
            }

            override suspend fun saveCallRequest(data: ChatResponse): ChatResponse {
                return data
            }
        }.asFlow()

    override fun getPetaniChat(id: Int): Flow<Resource<ChatPetaniResponse>> =
        object : InternetBoundResource<ChatPetaniResponse, ChatPetaniResponse>() {
            override suspend fun createCall(): Flow<Resource<ChatPetaniResponse>> {
                return repository.getPetaniChat(id)
            }

            override suspend fun saveCallRequest(data: ChatPetaniResponse): ChatPetaniResponse {
                return data
            }
        }.asFlow()

    override fun getLatestChatPetani(data: ChatLatestPetaniRequest): Flow<Resource<ChatResponse>> =
        object : InternetBoundResource<ChatResponse, ChatResponse>() {
            override suspend fun createCall(): Flow<Resource<ChatResponse>> {
                return repository.getLatestChatPetani(data)
            }

            override suspend fun saveCallRequest(data: ChatResponse): ChatResponse {
                return data
            }
        }.asFlow()
}