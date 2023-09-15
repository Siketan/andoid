package id.go.ngawikab.siketan.domain.auth.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


data class LoginRequest(
    @SerializedName("NIK", alternate = ["NIP"])
    val nik: String,
    val password: String
)