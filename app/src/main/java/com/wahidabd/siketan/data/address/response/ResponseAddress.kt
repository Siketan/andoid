package com.wahidabd.siketan.data.address.response

import com.google.gson.annotations.SerializedName
import com.wahidabd.siketan.domain.address.model.Address


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


data class ResponseAddress(
    @SerializedName("kecamatan", alternate = ["kelurahan"])
    val data: List<Address>
)