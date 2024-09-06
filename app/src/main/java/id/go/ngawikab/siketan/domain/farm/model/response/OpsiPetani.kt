package id.go.ngawikab.siketan.domain.farm.model.response

import android.os.Parcelable
import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpsiPetani(
    val nik: String? = null,
    val nama: String? = null,
    val id: Int,
    val desa: String? = null,
    val kecamatan: String? = null,
): Parcelable

fun Petani.todDomain(): OpsiPetani =
    OpsiPetani(
        nik = nik,
        nama = nama,
        id = id!!,
        desa = desa,
        kecamatan = kecamatan,
    )
