package com.wahidabd.siketan.data.auth.model.user

import com.wahidabd.library.utils.common.emptyString
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by Wahid on 8/9/2023.
 * Github github.com/wahidabd.
 */


data class UserEditeRequest(
    val id: Int? = 0,
    val NIK: String? = emptyString(),
    val nama: String? = emptyString(),
    val NoWa: String? = emptyString(),
    val alamat: String? = emptyString(),
    val kecamatan: String? = emptyString(),
    val desa: String? = emptyString(),
    val foto: File? = null
) {
    fun toBody(): MultipartBody =
        MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addFormDataPart("NIK", NIK.toString())
            addFormDataPart("nama", nama.toString())
            addFormDataPart("NoWa", NoWa.toString())
            addFormDataPart("alamat", alamat.toString())
            addFormDataPart("desa", desa.toString())
            addFormDataPart("kecamatan", kecamatan.toString())
            if (foto != null){
                addFormDataPart("foto", foto.name ,foto.asRequestBody("image/jpg".toMediaTypeOrNull()))
            }
        }.build()
}
