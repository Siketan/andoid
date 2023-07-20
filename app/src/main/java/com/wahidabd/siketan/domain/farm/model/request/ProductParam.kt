package com.wahidabd.siketan.domain.farm.model.request

import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.siketan.utils.convertToRequestBody
import okhttp3.RequestBody
import java.io.File


/**
 * Created by Wahid on 6/18/2023.
 * Github github.com/wahidabd.
 */


data class ProductParam(
    val nik: String? = emptyString(),
    val profesiPenjual: String? = emptyString(),
    val namaProduct: String? = emptyString(),
    val stok: Int? = 0,
    val satuan: String? = emptyString(),
    val harga: String? = emptyString(),
    val deskripsi: String? = emptyString(),
    val fotoTanaman: File? = null,
    val status: String = "Tersedia"
)