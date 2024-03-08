package id.go.ngawikab.siketan.data.farm.model.farm.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PlantFarmerResponse(


	@field:SerializedName("data")
	val data: List<PlantFarmerData> = emptyList(),

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class PlantFarmerData(

	@field:SerializedName("prakiraanLuasPanen")
	val prakiraanLuasPanen: Int? = null,

	@field:SerializedName("periodeBulanTanam")
	val periodeBulanTanam: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("deletedAt")
	val deletedAt: String? = null,

	@field:SerializedName("statusKepemilikanLahan")
	val statusKepemilikanLahan: String? = null,

	@field:SerializedName("prakiraanBulanPanen")
	val prakiraanBulanPanen: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("periodeMusimTanam")
	val periodeMusimTanam: String? = null,

	@field:SerializedName("prakiraanProduksiPanen")
	val prakiraanProduksiPanen: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("luasLahan")
	val luasLahan: String? = null,

	@field:SerializedName("komoditas")
	val komoditas: String? = null,

	@field:SerializedName("fk_petaniId")
	val fkPetaniId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
):Parcelable
