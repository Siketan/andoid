package id.go.ngawikab.siketan.data.auth.model.user

import android.os.Parcelable
import id.go.ngawikab.siketan.domain.address.model.Address
import kotlinx.parcelize.Parcelize

data class OpsiPenyuluhResponse(
	val dataDaftarPenyuluh: List<Penyuluh> ,
	val message: String? = null
)

@Parcelize
data class Penyuluh(
	val desaData: Address? = null,
	val noTelp: String? = null,
	val alamat: String? = null,
	val nik: String? = null,
	val accountID: String? = null,
	val createdAt: String? = null,
	val password: String? = null,
	val deletedAt: String? = null,
	val nama: String? = null,
	val foto: String? = null,
	val kecamatanData: Address? = null,
	val desaBinaan: String? = null,
	val id: Int,
	val namaProduct: String? = null,
	val kecamatanBinaan: String? = null,
	val email: String? = null,
	val updatedAt: String? = null
): Parcelable


