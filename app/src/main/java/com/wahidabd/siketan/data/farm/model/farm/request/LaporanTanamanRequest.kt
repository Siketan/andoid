package com.wahidabd.siketan.data.farm.model.farm.request

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by Wahid on 8/18/2023.
 * Github github.com/wahidabd.
 */


data class LaporanTanamanRequest(
    val tanamanPetaniId: Int,
    val tanggalLaporan: String,
    val komdisiTanaman: String,
    val deskripsi: String,
    val file: File? = null
){
    fun toBody(): MultipartBody =
        MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addFormDataPart("tanamanPetaniId", tanamanPetaniId.toString())
            addFormDataPart("tanggalLaporan", tanggalLaporan)
            addFormDataPart("komdisiTanaman", komdisiTanaman)
            addFormDataPart("deskripsi", deskripsi)
            if (file != null){
                addFormDataPart("fotoTanaman", file.name, file.asRequestBody("image/jpg".toMediaTypeOrNull()))
            }
        }.build()
}
