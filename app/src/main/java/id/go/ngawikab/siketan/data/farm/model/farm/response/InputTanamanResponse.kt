package id.go.ngawikab.siketan.data.farm.model.farm.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 8/16/2023.
 * Github github.com/wahidabd.
 */


data class InputTanamanResponse(
    val message: String,
    val dataTanamanPetani: InputDataTanamanResponse
)

data class InputDataTanamanResponse(
    val dataPersonId: Int? = 0,
    val statusLahan: String? = emptyString(),
    val luasLahan: String? = emptyString(),
    val kategori: String? = emptyString(),
    val jenisPanen: String? = emptyString(),
    val komoditas: String? = emptyString(),
    val musimTanam: Int? = 0,
    val tanggalTanam: String? = emptyString(),
    val perkiraanPanen: String? = emptyString(),
    val perkiraanHasilPanen: Int? = 0,
    val realisasiHasilPanen: Int? = 0,
)
