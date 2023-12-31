package id.go.ngawikab.siketan.data.chat.remote

import id.go.ngawikab.siketan.data.chat.model.response.ChatPetaniResponse
import id.go.ngawikab.siketan.data.chat.model.response.ChatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Wahid on 8/25/2023.
 * Github github.com/wahidabd.
 */


interface ChatApiClient {

    @GET("chat/messages/penyuluh")
    suspend fun getLatestChat(
        @Query("desa") desa: String,
        @Query("userId") userId: Int
    ): Response<ChatResponse>

    @GET("chat/petani/{id}")
    suspend fun getPetaniChat(
        @Path("id") id: Int
    ): Response<ChatPetaniResponse>

    @GET("chat/msesages/petani")
    suspend fun getLatestChatPetani(
        @Query("userId") userId: Int,
        @Query("partnerId") partnerId: Int
    ): Response<ChatResponse>

}