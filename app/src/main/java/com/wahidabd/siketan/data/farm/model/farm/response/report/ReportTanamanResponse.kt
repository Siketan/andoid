package go.ngawikab.siketan.data.farm.model.farm.response.report

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 8/19/2023.
 * Github github.com/wahidabd.
 */


data class ReportTanamanResponse(
    val daftarTani: DaftarTaniTanamanResponse
)

data class DaftarTaniTanamanResponse(
    val laporanTanams: List<ReportTanamanDataResponse>
)

data class ReportTanamanDataResponse(
    val id: Int? = 0,
    val tanamanPetaniId: Int? = 0,
    val komdisiTanaman: String? = emptyString(),
    val deskripsi: String? = emptyString(),
    val fotoTanaman: String? = emptyString(),
    val tanggalLaporan: String? = emptyString()
)
