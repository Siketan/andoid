package id.go.ngawikab.siketan.data.chat.model.request

import org.json.JSONObject


/**
 * Created by Wahid on 9/1/2023.
 * Github github.com/wahidabd.
 */


data class ChatSendRequest(
    val fromUserId: Int,
    val toUserId: Int,
    val message: String,
    val chatId: Int,
    val waktu: String
){
    fun toObj(): JSONObject{
        val obj = JSONObject()
        obj.put("fromId", fromUserId)
        obj.put("toUserId", toUserId)
        obj.put("message", message)
        obj.put("chatId", chatId)
        obj.put("waktu", waktu)
        return obj
    }
}