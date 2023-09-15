package id.go.ngawikab.siketan.data.auth.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

data class RegisterDataRequest(
    @SerializedName("NIK")
    val nik: String,
    val no_wa: String,
    val nama: String,
    val password: String,
)