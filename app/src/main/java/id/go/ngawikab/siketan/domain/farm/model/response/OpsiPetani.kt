package id.go.ngawikab.siketan.domain.farm.model.response

import android.os.Parcelable
import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani
import id.go.ngawikab.siketan.domain.address.model.Address
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpsiPetani(
    val nik: String? = null,
    val nama: String? = null,
    val id: Int,
    val desaData: Address? = null,
    val kecamatanData: Address? = null,
): Parcelable

fun Petani.todDomain(): OpsiPetani =
    OpsiPetani(
        nik = nik,
        nama = nama,
        id = id!!,
        desaData = desaData,
        kecamatanData = kecamatanData,
    )
