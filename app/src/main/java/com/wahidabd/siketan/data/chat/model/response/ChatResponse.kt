package com.wahidabd.siketan.data.chat.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 8/26/2023.
 * Github github.com/wahidabd.
 */


data class ChatResponse(
    val user: ChatUserResponse,
    val chatId: Int? = 0,
    val messages: List<ChatMessageResponse>
)

data class ChatUserResponse(
    val nama: String? = emptyString(),
    val foto: String? = emptyString()
)

data class ChatMessageResponse(
    val id: Int? = 0,
    val attachmentId: Int? = null,
    val pesan: String? = emptyString(),
    val chatId: Int? = 0,
    val fromId: Int? = 0,
    val createdAt: String? = emptyString(),
    val updatedAt: String? = emptyString(),
    val waktu: String? = emptyString(),
    val attachment: AttachmentChat? = null
)

data class AttachmentChat(
    val link: String
)