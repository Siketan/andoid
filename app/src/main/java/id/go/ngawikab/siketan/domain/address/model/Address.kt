package id.go.ngawikab.siketan.domain.address.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


data class Address(
    @SerializedName("id")
    val id: Long,
    @SerializedName("kecamatanId")
    val idCity: Long,
    @SerializedName("nama")
    val name: String
)
