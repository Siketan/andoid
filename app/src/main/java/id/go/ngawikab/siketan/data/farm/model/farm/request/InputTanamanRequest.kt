package id.go.ngawikab.siketan.data.farm.model.farm.request


/**
 * Created by Wahid on 8/11/2023.
 * Github github.com/wahidabd.
 */
/*
* {
    "fk_petaniId": 1,
    "jenis": "",
    "kategori": "TANAMAN PERKEBUNAN",
    "komoditas": "Cengkeh",
    "luasLahan": 50,
    "periodeBulanTanam": "Mei",
    "periodeMusimTanam": "Tanaman Semusim",
    "prakiraanBulanPanen": "Juni",
    "prakiraanLuasPanen": 70,
    "prakiraanProduksiPanen": 80,
    "statusKepemilikanLahan": "TANAH SEWA"
}
*
* */

data class InputTanamanRequest(
    val fk_petaniId: Int,
    val statusKepemilikanLahan: String,
    val luasLahan: String,
    val kategori: String,
    val jenis: String,
    val periodeMusimTanam:String,
    val komoditas: String,
    val periodeBulanTanam: String,
    val prakiraanBulanPanen: String,
    val prakiraanLuasPanen: Int,
    val prakiraanProduksiPanen: Int? = null,

)
