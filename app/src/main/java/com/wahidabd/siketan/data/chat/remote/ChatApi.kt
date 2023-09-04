package com.wahidabd.siketan.data.chat.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.chat.model.response.ChatPetaniResponse
import com.wahidabd.siketan.data.chat.model.response.ChatResponse
import retrofit2.Response


/**
 * Created by Wahid on 8/25/2023.
 * Github github.com/wahidabd.
 */


class ChatApi(private val api: ChatApiClient) : WebApi, ChatApiClient {
    override suspend fun getLatestChat(desa: String, userId: Int): Response<ChatResponse> {
        return api.getLatestChat(desa, userId)
    }

    override suspend fun getPetaniChat(id: Int): Response<ChatPetaniResponse> {
        return api.getPetaniChat(id)
    }

    override suspend fun getLatestChatPetani(userId: Int, partnerId: Int): Response<ChatResponse> {
        return api.getLatestChatPetani(userId, partnerId)
    }
}