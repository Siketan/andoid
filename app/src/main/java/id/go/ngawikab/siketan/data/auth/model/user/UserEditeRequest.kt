package id.go.ngawikab.siketan.data.auth.model.user

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
    val nik: String? = emptyString(),
    val nama: String? = emptyString(),
    val whatsapp: String? = emptyString(),
    val alamat: String? = emptyString(),
    val kecamatan: String? = emptyString(),
    val desa: String? = emptyString(),
    val foto: File? = null,
    val password: String? = emptyString(),
    val passwordBaru: String? = emptyString(),
) {
    fun toBody(): MultipartBody =
        MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addFormDataPart("nik", nik.toString())
            addFormDataPart("nama", nama.toString())
            addFormDataPart("whatsapp", whatsapp.toString())
            addFormDataPart("alamat", alamat.toString())
            addFormDataPart("desa", desa.toString())
            addFormDataPart("kecamatan", kecamatan.toString())
            addFormDataPart("password", password.toString())
            addFormDataPart("passwordBaru", passwordBaru.toString())
            if (foto != null){
                addFormDataPart("foto", foto.name ,foto.asRequestBody("image/jpg".toMediaTypeOrNull()))
            }
        }.build()
}
