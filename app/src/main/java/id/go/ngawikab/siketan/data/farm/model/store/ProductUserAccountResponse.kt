package id.go.ngawikab.siketan.data.farm.model.store

import com.google.gson.annotations.SerializedName

data class ProductUserAccountResponse(

	@field:SerializedName("isVerified")
	val isVerified: Boolean? = null,

	@field:SerializedName("no_wa")
	val noWa: String? = null,

	@field:SerializedName("dataPetani")
	val dataPetani: DataPetaniResponse? = null,

	@field:SerializedName("accountID")
	val accountID: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("peran")
	val peran: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("dataPenyuluh")
	val dataPenyuluh: DataPenyuluhResponse? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

