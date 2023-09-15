package id.go.ngawikab.siketan.data.farm.model.farm.response

import android.os.Parcelable
import com.wahidabd.library.utils.common.emptyString
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 8/17/2023.
 * Github github.com/wahidabd.
 */


data class TanamanPetaniResponse(
    val message: String,
    val tani: UserTani
)

data class UserTani(
    val tanamanPetanis: List<TanamanPetaniDataResponse>
)

@Parcelize
data class TanamanPetaniDataResponse(
    val id: Int? = null,
    val dataPersonId: Int? = null,
    val statusLahan: String? = emptyString(),
    val luasLahan: String? = emptyString(),
    val kategori: String? = emptyString(),
    val jenisPanen: String? = emptyString(),
    val jenis: String? = emptyString(),
    val komoditas: String? = emptyString(),
    val musimTanam: Int? = null,
    val tanggalTanam: String? = emptyString(),
    val perkiraanPanen: String? = emptyString(),
    val perkiraanHasilPanen: Int? = null,
    val realisasiHasilPanen: Int? = null,
): Parcelable
