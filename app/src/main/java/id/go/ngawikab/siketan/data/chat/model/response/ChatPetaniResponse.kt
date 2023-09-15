package id.go.ngawikab.siketan.data.chat.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 8/26/2023.
 * Github github.com/wahidabd.
 */


data class ChatPetaniResponse(
    val petani: List<ChatPetaniDataResponse>
)

data class ChatPetaniDataResponse(
    val id: Int,
    val nama: String,
    val foto: String? = emptyString(),
    val desa: String? = emptyString(),
    val role: String? = emptyString()
)
