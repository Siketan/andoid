package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.siketan.data.farm.model.farm.request.ProductRequest
import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.data.farm.model.store.response.ProductAddResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


interface FarmApiClient {

    @GET("info-tani")
    suspend fun getInfoTani(): Response<InfoTaniDataResponse<InfoTaniResponse>>

    @GET("event-tani")
    suspend fun getEvent()
            : Response<InfoTaniDataResponse<EventTaniResponse>>

    @GET("product-petani")
    suspend fun getProduct(): Response<ProductDataResponse>

    @POST("daftar-penjual/add")
    suspend fun postStore(
        @Body body: MultipartBody
    ): Response<ProductAddResponse>

    @POST("jurnal-kegiatan/add")
    suspend fun addJournal(
        @Body body: MultipartBody
    ): Response<ProductAddResponse>

}