package id.go.ngawikab.siketan.domain.auth.model

import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.auth.model.user.UserResponse
import id.go.ngawikab.siketan.domain.farm.model.response.Journal
import timber.log.Timber

data class User(
    val nik: String? = emptyString(),
    val nkk: String? = emptyString(),
    val NoWa: String? = emptyString(),
    val alamat: String? = emptyString(),
    val createdAt: String? = emptyString(),
    val desa: String? = emptyString(),
    val foto: String? = emptyString(),
    val id: Int? = 0,
    val jurnalKegiatanId: Int? = 0,
    val kecamatan: String? = emptyString(),
    val kecamatanBinaan: String?= emptyString(),
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
    val updatedAt: String? = emptyString(),
    val jurnalHarian: Journal? = null

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
        kecamatanBinaan =kecamatanBinaan,
        kelompokId,
        laporanTanamId,
        nama,
        password,
        presesiKehadiranId,
        rattingId,
        responseRatingId,
        riwayatChatId,
        role = if(kecamatanBinaan==null || kecamatanBinaan == emptyString()){
            Timber.tag("User NIP ").d(NIP)
            "petani"}else{
            Timber.tag("User NIP ").d(NIP)
                "penyuluh"},
        tanamanPetaniId,
        updatedAt,
    )