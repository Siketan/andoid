package id.go.ngawikab.siketan.data.auth.model.user

data class OpsiPenyuluhResponse(
	val dataDaftarPenyuluh: List<Penyuluh> ,
	val message: String? = null
)

data class Penyuluh(
	val desa: String? = null,
	val noTelp: String? = null,
	val alamat: String? = null,
	val nik: String? = null,
	val accountID: String? = null,
	val createdAt: String? = null,
	val password: String? = null,
	val deletedAt: Any? = null,
	val nama: String? = null,
	val foto: String? = null,
	val kecamatan: String? = null,
	val desaBinaan: String? = null,
	val id: Int ,
	val namaProduct: String? = null,
	val kecamatanBinaan: String? = null,
	val email: String? = null,
	val updatedAt: String? = null
)



