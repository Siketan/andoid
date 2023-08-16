package com.wahidabd.siketan.data.auth.model.user

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 6/26/2023.
 * Github github.com/wahidabd.
 */


data class UserResponse(
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
