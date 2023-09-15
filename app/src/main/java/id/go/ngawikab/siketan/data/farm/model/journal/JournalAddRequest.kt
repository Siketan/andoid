package id.go.ngawikab.siketan.data.farm.model.journal

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


data class JournalAddRequest(
    val nip: String,
    val judul: String,
    val tanggal: String,
    val uraian: String,
    val status: String,
    val file: File
) {
    fun toRequestBody(): MultipartBody =
        MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addFormDataPart("NIP", nip)
            addFormDataPart("judul", judul)
            addFormDataPart("tanggalDibuat", tanggal)
            addFormDataPart("uraian", uraian)
            addFormDataPart("statusJurnal", status)
            addFormDataPart("gambar", file.name, file.asRequestBody("image/jpg".toMediaTypeOrNull()))
        }.build()
}