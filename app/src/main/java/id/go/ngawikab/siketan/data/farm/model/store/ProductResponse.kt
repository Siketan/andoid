package id.go.ngawikab.siketan.data.farm.model.store

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("profesiPenjual") var profesiPenjual: String? = null,
    @SerializedName("namaProducts") var namaProducts: String? = null,
    @SerializedName("stok") var stok: Int? = null,
    @SerializedName("satuan") var satuan: String? = null,
    @SerializedName("harga") var harga: String? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("fotoTanaman") var fotoTanaman: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("accountID") var accountID: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("deletedAt") var deletedAt: String? = null,
    @SerializedName("tbl_akun") var tblAkun: ProductUserAccountResponse? = null
)