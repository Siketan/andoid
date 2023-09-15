package id.go.ngawikab.siketan.data.chat

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestPetaniRequest
import id.go.ngawikab.siketan.data.chat.model.request.ChatLatestRequest
import id.go.ngawikab.siketan.data.chat.model.response.ChatPetaniResponse
import id.go.ngawikab.siketan.data.chat.model.response.ChatResponse
import id.go.ngawikab.siketan.data.chat.remote.ChatApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by Wahid on 8/25/2023.
 * Github github.com/wahidabd.
 */


class ChatDataSource(
    api: ChatApi,
    private val err: ErrorParses
) : ChatRepository {

    override val dbService: LocalDb? = null
    override val webService = api


    override suspend fun getLatestChat(data: ChatLatestRequest): Flow<Resource<ChatResponse>> =
        flow {
            enqueue(
                data.desa,
                data.userId,
                err::convertGenericError,
                webService::getLatestChat,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun getPetaniChat(id: Int): Flow<Resource<ChatPetaniResponse>> = flow {
        enqueue(
            id,
            err::convertGenericError,
            webService::getPetaniChat,
            onEmit = { emit(it) }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun getLatestChatPetani(data: ChatLatestPetaniRequest): Flow<Resource<ChatResponse>> =
        flow {
            enqueue(
                data.userId,
                data.partnerId,
                err::convertGenericError,
                webService::getLatestChatPetani,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

}