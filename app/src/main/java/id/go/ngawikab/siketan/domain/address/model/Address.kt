package id.go.ngawikab.siketan.domain.address.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */

@Parcelize
data class Address(
    @SerializedName("id")
    val id: Long,
    @SerializedName("kecamatanId")
    val kecamatanId: Long? = null,
    @SerializedName("nama")
    val nama: String
): Parcelable
