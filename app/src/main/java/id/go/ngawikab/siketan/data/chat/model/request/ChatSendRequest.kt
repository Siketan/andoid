package id.go.ngawikab.siketan.data.chat.model.request

import com.wahidabd.library.utils.common.emptyString
import org.json.JSONObject
import java.io.File


/**
 * Created by Wahid on 9/1/2023.
 * Github github.com/wahidabd.
 */


data class ChatSendRequest(
    val fromUserId: Int,
    val toUserId: Int,
    val message: String,
    val chatId: Int,
    val waktu: String,
    val image: String? = emptyString()
){
    fun toObj(): JSONObject{
        val obj = JSONObject()
        obj.put("fromId", fromUserId)
        obj.put("toUserId", toUserId)
        obj.put("message", message)
        obj.put("chatId", chatId)
        obj.put("waktu", waktu)
        obj.put("image", image)
        return obj
    }
}