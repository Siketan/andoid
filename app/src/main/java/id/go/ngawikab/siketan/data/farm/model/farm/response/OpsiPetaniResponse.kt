package id.go.ngawikab.siketan.data.farm.model.farm.response

import com.google.gson.annotations.SerializedName
import id.go.ngawikab.siketan.domain.address.model.Address

data class OpsiPetaniResponse(
	@SerializedName("petanis")
	val petanis: List<Petani>,
	@SerializedName("message")
	val message: String? = null
)

data class Petani(
	@SerializedName("id") val id: Int? = null,
	@SerializedName("desaData") val desaData: Address? = null,
	@SerializedName("fkKelompokId") val fkKelompokId: Int? = null,
	@SerializedName("fkPenyuluhId")	val fkPenyuluhId: Int? = null,
	@SerializedName("noTelp") val noTelp: String? = null,
	@SerializedName("nkk") val nkk: String? = null,
	@SerializedName("alamat") val alamat: String? = null,
	@SerializedName("nik") val nik: String? = null,
	@SerializedName("accountID") val accountID: String? = null,
	@SerializedName("createdAt") val createdAt: String? = null,
	@SerializedName("password")	val password: String? = null,
	@SerializedName("deletedAt") val deletedAt: Any? = null,
	@SerializedName("foto") val foto: String? = null,
	@SerializedName("nama") val nama: String? = null,
	@SerializedName("kecamatanData") val kecamatanData: Address? = null,
	@SerializedName("email") val email: String? = null,
	@SerializedName("updatedAt") val updatedAt: String? = null
)

