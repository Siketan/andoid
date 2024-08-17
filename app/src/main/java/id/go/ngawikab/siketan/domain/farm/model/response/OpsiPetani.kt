package id.go.ngawikab.siketan.domain.farm.model.response

import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani

data class OpsiPetani(
    val nik: String? = null,
    val nama: String? = null,
    val id: Int,
)

fun Petani.todDomain(): OpsiPetani =
    OpsiPetani(
        nik = nik,
        nama = nama,
        id = id
    )
