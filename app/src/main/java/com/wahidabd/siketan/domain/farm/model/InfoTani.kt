package com.wahidabd.siketan.domain.farm.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.siketan.data.farm.model.farm.InfoTaniResponse


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