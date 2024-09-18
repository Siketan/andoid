package id.go.ngawikab.siketan.data.address.response

import com.google.gson.annotations.SerializedName
import id.go.ngawikab.siketan.domain.address.model.Address


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


data class ResponseAddress(
    @SerializedName("data")
    val data: List<Address>
)