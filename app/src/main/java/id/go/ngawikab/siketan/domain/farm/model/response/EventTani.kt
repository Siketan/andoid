package id.go.ngawikab.siketan.domain.farm.model.response

import android.os.Parcelable
import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.farm.model.farm.response.EventTaniResponse
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 6/20/2023.
 * Github github.com/wahidabd.
 */

@Parcelize
data class EventTani(
    val createdAt: String? = emptyString(),
    val createdBy: String? = emptyString(),
    val fotoKegiatan: String? = emptyString(),
    val id: Int? = 0,
    val isi: String? = emptyString(),
    val namaKegiatan: String? = emptyString(),
    val peserta: String? = emptyString(),
    val tanggalAcara: String? = emptyString(),
    val tempat: String? = emptyString(),
    val updatedAt: String? = emptyString(),
    val waktuAcara: String? = emptyString()
):Parcelable

fun EventTaniResponse.toDomain(): EventTani =
    EventTani(
        createdAt,
        createdBy,
        fotoKegiatan,
        id,
        isi,
        namaKegiatan,
        peserta,
        tanggalAcara,
        tempat,
        updatedAt,
        waktuAcara
    )