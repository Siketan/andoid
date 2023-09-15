package id.go.ngawikab.siketan.domain.farm.model.response

import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


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
)

fun InfoTaniResponse.toDomain(): InfoTani =
    InfoTani(
        id, judul, tanggal, status, kategori, fotoBerita, createdBy, isi, createdAt, updatedAt
    )