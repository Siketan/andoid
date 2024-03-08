package id.go.ngawikab.siketan.domain.auth.model

import id.go.ngawikab.siketan.data.auth.model.user.OpsiPenyuluhResponse
import id.go.ngawikab.siketan.data.auth.model.user.Penyuluh
import id.go.ngawikab.siketan.data.farm.model.store.ProductUserAccountResponse
import id.go.ngawikab.siketan.domain.farm.model.response.ProductUserAccount

data class OpsiPenyuluh(
    val nik: String? = null,
    val nama: String? = null,
    val id: Int,
)

fun Penyuluh.todDomain(): OpsiPenyuluh =
    OpsiPenyuluh(
        nik = nik,
        nama = nama,
        id = id
    )

