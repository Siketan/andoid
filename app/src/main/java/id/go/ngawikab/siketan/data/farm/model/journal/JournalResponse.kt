package id.go.ngawikab.siketan.data.farm.model.journal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.go.ngawikab.siketan.data.auth.model.user.Penyuluh
import kotlinx.parcelize.Parcelize

data class JournalResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("newData")
	val newData: List<Jounal>
)
@Parcelize
data class Jounal(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("pengubah")
	val pengubah: String? = null,

	@field:SerializedName("deletedAt")
	val deletedAt: String? = null,

	@field:SerializedName("statusJurnal")
	val statusJurnal: String? = null,

	@field:SerializedName("fk_penyuluhId")
	val fkPenyuluhId: Int? = null,

	@field:SerializedName("uraian")
	val uraian: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("tanggalDibuat")
	val tanggalDibuat: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("dataPenyuluh")
	val dataPenyuluh: Penyuluh? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
): Parcelable
