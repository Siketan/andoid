package id.go.ngawikab.siketan.data.auth.model.user

import id.go.ngawikab.siketan.domain.auth.model.User

data class DetailPenyuluhResponse(
    val message: String,
    val dataDaftarPenyuluh: Penyuluh
)
