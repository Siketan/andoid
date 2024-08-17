package id.go.ngawikab.siketan.data.farm.model.farm.response

data class OpsiPetaniResponse(
	val petanis: List<Petani>,
	val message: String? = null
)

data class Petani(
	val desa: String? = null,
	val fkKelompokId: Int? = null,
	val fkPenyuluhId: Int? = null,
	val noTelp: String? = null,
	val nkk: String? = null,
	val alamat: String? = null,
	val nik: String? = null,
	val accountID: String? = null,
	val createdAt: String? = null,
	val password: String? = null,
	val deletedAt: Any? = null,
	val foto: String? = null,
	val nama: String? = null,
	val kecamatan: String? = null,
	val id: Int,
	val email: String? = null,
	val updatedAt: String? = null
)

