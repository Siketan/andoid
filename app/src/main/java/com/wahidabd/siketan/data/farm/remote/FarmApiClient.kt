package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.siketan.data.farm.model.farm.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.request.ProductRequest
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


interface FarmApiClient {

    @GET("info-tani")
    suspend fun getInfoTani(): Response<InfoTaniDataResponse>

    @GET("product-petani")
    suspend fun getProduct(): Response<ProductDataResponse>

//    @POST("daftar-penjual/add")
//    suspend fun postStore(
//        @Body data: ProductRequest
//    ): Response<>

}