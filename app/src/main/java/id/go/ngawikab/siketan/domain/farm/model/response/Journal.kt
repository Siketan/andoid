package id.go.ngawikab.siketan.domain.farm.model.response

import android.os.Parcelable
import com.wahidabd.library.utils.common.emptyString
import id.go.ngawikab.siketan.data.auth.model.user.Penyuluh
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse
import id.go.ngawikab.siketan.data.farm.model.journal.Jounal
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 7/21/2023.
 * Github github.com/wahidabd.
 */

data class Journal(
    val id: Int? = 0,
    val judul: String? = emptyString(),
    val tanggalDibuat: String? = emptyString(),
    val uraian: String? = emptyString(),
    val gambar: String? = emptyString(),
    val statusJurnal: String? = emptyString(),
    val createdAt: String? = emptyString(),
    val updatedAt: String? = emptyString()
)
