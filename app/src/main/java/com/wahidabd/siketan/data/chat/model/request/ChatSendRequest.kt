package com.wahidabd.siketan.data.chat.model.request

import org.json.JSONObject


/**
 * Created by Wahid on 9/1/2023.
 * Github github.com/wahidabd.
 */


data class ChatSendRequest(
    val fromUserId: Int,
    val toUserId: Int,
    val message: String,
    val type: String? = "personal",
    val chatId: Int
){
    fun toObj(): JSONObject{
        val obj = JSONObject()
        obj.put("fromId", fromUserId)
        obj.put("toUserId", toUserId)
        obj.put("message", message)
        obj.put("type", type)
        obj.put("chatId", chatId)
        return obj
    }
}