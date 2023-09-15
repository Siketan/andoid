package id.go.ngawikab.siketan.domain.farm.model

import id.go.ngawikab.siketan.data.farm.model.farm.request.ProductRequest
import id.go.ngawikab.siketan.domain.farm.model.request.ProductParam


/**
 * Created by Wahid on 7/1/2023.
 * Github github.com/wahidabd.
 */


fun ProductParam.toRequest(): ProductRequest =
    ProductRequest(
        nik, profesiPenjual, namaProduct, stok, satuan, harga, deskripsi, fotoTanaman, status
    )