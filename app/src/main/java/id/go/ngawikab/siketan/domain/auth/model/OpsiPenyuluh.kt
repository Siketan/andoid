package id.go.ngawikab.siketan.domain.auth.model

import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.auth.model.user.Penyuluh

data class OpsiPenyuluh(
    val nik: String? = emptyString(),
    val nama: String? = emptyString(),
    val id: Int,
)

fun Penyuluh.todDomain(): OpsiPenyuluh =
    OpsiPenyuluh(
        nik = nik,
        nama = nama,
        id = id
    )

