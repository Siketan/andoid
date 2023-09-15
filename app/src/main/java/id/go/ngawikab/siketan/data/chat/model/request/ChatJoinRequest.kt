package id.go.ngawikab.siketan.data.chat.model.request

import org.json.JSONObject


/**
 * Created by Wahid on 9/1/2023.
 * Github github.com/wahidabd.
 */


data class ChatJoinRequest(
    val id: Int,
    val chatId: Int
){
    fun toObj(): JSONObject {
        val obj = JSONObject()
        obj.put("id", id)
        obj.put("chatId", chatId)
        return obj
    }
}
