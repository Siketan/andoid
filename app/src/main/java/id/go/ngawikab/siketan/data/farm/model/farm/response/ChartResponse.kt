package id.go.ngawikab.siketan.data.farm.model.farm.response

data class ChartResponse(
	val data: List<ChartDataResponse?>? = null,
	val message: String? = null
)

data class ChartDataResponse(
	val periodeMusimTanam: String? = null,
	val jenis: String? = null,
	val count: Int? = null,
	val kategori: String? = null,
	val komoditas: String? = null
)

