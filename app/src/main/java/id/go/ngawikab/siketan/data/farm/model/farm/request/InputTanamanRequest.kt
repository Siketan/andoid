package id.go.ngawikab.siketan.data.farm.model.farm.request


/**
 * Created by Wahid on 8/11/2023.
 * Github github.com/wahidabd.
 */


data class InputTanamanRequest(
    val dataPersonId: Int,
    val statusLahan: String,
    val luasLahan: String,
    val kategori: String,
    val jenisPanen: String,
    val jenis: String,
    val komoditas: String,
    val musimTanam: Int,
    val tanggalTanam: String,
    val perkiraanPanen: String,
    val perkiraanHasilPanen: Int,
    val realisasiHasilPanen: Int? = null,
)
