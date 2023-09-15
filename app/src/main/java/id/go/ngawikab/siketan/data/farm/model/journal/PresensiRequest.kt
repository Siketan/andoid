package id.go.ngawikab.siketan.data.farm.model.journal

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by Wahid on 7/21/2023.
 * Github github.com/wahidabd.
 */


data class PresensiRequest(
    val nip: String,
    val date: String,
    val title: String,
    val desc: String,
    val file: File
){
    fun toRequestBody(): MultipartBody =
        MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addFormDataPart("NIP", nip)
            addFormDataPart("tanggalPresensi", date)
            addFormDataPart("judulKegiatan", title)
            addFormDataPart("deskripsiKegiatan", desc)
            addFormDataPart("FotoKegiatan", file.name, file.asRequestBody("image/jpg".toMediaTypeOrNull()))
        }.build()
}
