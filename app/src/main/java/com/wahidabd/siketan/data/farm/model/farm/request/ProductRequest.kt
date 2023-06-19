package com.wahidabd.siketan.data.farm.model.farm.request

import com.wahidabd.siketan.utils.toRequestBody
import okhttp3.RequestBody
import java.io.File


/**
 * Created by Wahid on 6/18/2023.
 * Github github.com/wahidabd.
 */


data class ProductRequest(
    val nik: String? = null,
    val profesiPenjual: String? = null,
    val namaProduct: String? = null,
    val stok: Int? = null,
    val satuan: String? = null,
    val harga: String? = null,
    val deskripsi: String? = null,
    val fotoTanaman: File? = null
) {
    fun toBody(): RequestBody =
        toRequestBody(
            listOf(
                nik,
                profesiPenjual,
                namaProduct,
                stok,
                satuan,
                harga,
                deskripsi,
                fotoTanaman
            )
        )
}
