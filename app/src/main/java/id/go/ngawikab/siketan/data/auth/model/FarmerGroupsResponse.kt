package id.go.ngawikab.siketan.data.auth.model

import com.google.gson.annotations.SerializedName

data class FarmerGroupsResponse(

	@field:SerializedName("kelompokTani")
	val kelompokTani: List<FarmerGroup>,

	@field:SerializedName("message")
	val message: String? = null
)

data class FarmerGroup(

	@field:SerializedName("desa")
	val desa: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: Any? = null,

	@field:SerializedName("penyuluh")
	val penyuluh: Any? = null,

	@field:SerializedName("kecamatan")
	val kecamatan: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("namaKelompok")
	val namaKelompok: String? = null,

	@field:SerializedName("gapoktan")
	val gapoktan: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Any? = null
)
