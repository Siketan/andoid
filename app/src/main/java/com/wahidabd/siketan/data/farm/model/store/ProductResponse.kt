package com.wahidabd.siketan.data.farm.model.store

data class ProductResponse(
    val createdAt: String? = null,
    val dataPerson: ProductUserResponse? = null,
    val dataPersonId: Int? = null,
    val deskripsi: String? = null,
    val fotoTanaman: String? = null,
    val harga: String? = null,
    val id: Int? = null,
    val namaProducts: String? = null,
    val profesiPenjual: String? = null,
    val satuan: String? = null,
    val status: String? = null,
    val stok: Int? = null,
    val updatedAt: String? = null
)