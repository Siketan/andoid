package id.go.ngawikab.siketan.domain.farm.model.response

import android.os.Parcelable
import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */

@Parcelize
data class InfoTani(
    val id: Int? = 0,
    val judul: String? = emptyString(),
    val tanggal: String? = emptyString(),
    val status: String? = emptyString(),
    val kategori: String? = emptyString(),
    val fotoBerita: String? = emptyString(),
    val createdBy: String? = emptyString(),
    val isi: String? = emptyString(),
    val createdAt: String? = emptyString(),
    val updatedAt: String? = emptyString()
):Parcelable

fun InfoTaniResponse.toDomain(): InfoTani =
    InfoTani(
        id, judul, tanggal, status, kategori, fotoBerita, createdBy, isi, createdAt, updatedAt
    )