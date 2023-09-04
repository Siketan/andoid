package go.ngawikab.siketan.data.chat

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.chat.model.request.ChatLatestPetaniRequest
import com.wahidabd.siketan.data.chat.model.request.ChatLatestRequest
import com.wahidabd.siketan.data.chat.model.response.ChatPetaniResponse
import com.wahidabd.siketan.data.chat.model.response.ChatResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 8/25/2023.
 * Github github.com/wahidabd.
 */


interface ChatRepository : BaseRepository {

    suspend fun getLatestChat(data: ChatLatestRequest): Flow<Resource<ChatResponse>>
    suspend fun getPetaniChat(id: Int): Flow<Resource<ChatPetaniResponse>>
    suspend fun getLatestChatPetani(data: ChatLatestPetaniRequest): Flow<Resource<ChatResponse>>

}