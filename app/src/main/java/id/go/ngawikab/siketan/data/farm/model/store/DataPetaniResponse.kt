package id.go.ngawikab.siketan.data.farm.model.store

import com.google.gson.annotations.SerializedName

data class DataPetaniResponse(

    val desa: String? = null,

    @field:SerializedName("fk_kelompokId")
    val fkKelompokId: Int? = null,

    @field:SerializedName("fk_penyuluhId")
    val fkPenyuluhId: Int? = null,

    @field:SerializedName("noTelp")
    val noTelp: String? = null,

    @field:SerializedName("nkk")
    val nkk: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("nik")
    val nik: String? = null,

    @field:SerializedName("accountID")
    val accountID: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("deletedAt")
    val deletedAt: Any? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("kecamatan")
    val kecamatan: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
