package com.wahidabd.siketan.domain.farm.model

import com.wahidabd.siketan.data.farm.model.farm.request.ProductRequest
import com.wahidabd.siketan.domain.farm.model.request.ProductParam


/**
 * Created by Wahid on 7/1/2023.
 * Github github.com/wahidabd.
 */


fun ProductParam.toRequest(): ProductRequest =
    ProductRequest(
        nik, profesiPenjual, namaProduct, stok, satuan, harga, deskripsi, fotoTanaman, status
    )