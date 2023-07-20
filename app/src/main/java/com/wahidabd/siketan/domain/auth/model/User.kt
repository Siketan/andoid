package com.wahidabd.siketan.domain.auth.model

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.siketan.data.auth.model.UserResponse

data class User(
    val NIK: String? = emptyString(),
    val NIP: String? = emptyString(),
    val NoWa: String? = emptyString(),
    val alamat: String? = emptyString(),
    val createdAt: String? = emptyString(),
    val desa: String? = emptyString(),
    val foto: String? = emptyString(),
    val id: Int? = 0,
    val jurnalKegiatanId: Int? = 0,
    val kecamatan: String? = emptyString(),
    val kelompokId: Int? = 0,
    val laporanTanamId: Int? = 0,
    val nama: String? = emptyString(),
    val password: String? = emptyString(),
    val presesiKehadiranId: Int? = 0,
    val rattingId: Int? = 0,
    val responseRatingId: Int? = 0,
    val riwayatChatId: Int? = 0,
    val role: String? = emptyString(),
    val tanamanPetaniId: Int? = 0,
    val updatedAt: String? = emptyString()
)

fun UserResponse.toDomain(): User =
    User(
        NIK,
        NIP,
        NoWa,
        alamat,
        createdAt,
        desa,
        foto,
        id,
        jurnalKegiatanId,
        kecamatan,
        kelompokId,
        laporanTanamId,
        nama,
        password,
        presesiKehadiranId,
        rattingId,
        responseRatingId,
        riwayatChatId,
        role,
        tanamanPetaniId,
        updatedAt
    )