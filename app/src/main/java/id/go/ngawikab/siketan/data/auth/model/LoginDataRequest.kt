package id.go.ngawikab.siketan.data.auth.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


data class LoginDataRequest(
    @SerializedName("NIK", alternate = ["NIP"])
    val email: String,
    val password: String
)

data class LoginPenyuluhRequest(
    @SerializedName("NIP")
    val nip: String,
    val password: String
)