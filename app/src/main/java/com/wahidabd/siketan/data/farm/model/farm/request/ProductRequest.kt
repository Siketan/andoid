package com.wahidabd.siketan.data.farm.model.farm.request

import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by Wahid on 6/18/2023.
 * Github github.com/wahidabd.
 */


data class ProductRequest(
    @SerializedName("NIK")
    val nik: String? = null,
    val profesiPenjual: String? = null,
    val namaProducts: String? = null,
    val stok: Int? = null,
    val satuan: String? = null,
    val harga: String? = null,
    val deskripsi: String? = null,
    val fotoTanaman: File? = null,
    val status: String = "Tersedia"
) {
    fun toBody(): MultipartBody =
        MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addFormDataPart("NIK", nik.toString())
            addFormDataPart("profesiPenjual", profesiPenjual.toString())
            addFormDataPart("namaProducts", namaProducts.toString())
            addFormDataPart("stok", stok.toString())
            addFormDataPart("satuan", satuan.toString())
            addFormDataPart("harga", harga.toString())
            addFormDataPart("deskripsi", deskripsi.toString())
            addFormDataPart("status", status)
            fotoTanaman?.let {
                addFormDataPart(
                    "fotoTanaman",
                    fotoTanaman.name,
                    it.asRequestBody("image/jpg".toMediaTypeOrNull())
                )
            }
        }.build()
}
