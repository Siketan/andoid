package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.journal.JournalResponse
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


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
    ): Response<GenericAddResponse>
    
    @GET("jurnal-kegiatan")
    suspend fun getJournal(): Response<JournalResponse>

    @POST("jurnal-kegiatan/add")
    suspend fun addJournal(
        @Body body: MultipartBody
    ): Response<GenericAddResponse>

    @POST("presensi-kehadiran/add")
    suspend fun addPresensi(
        @Body body: MultipartBody
    ): Response<GenericAddResponse>

    @GET("chart")
    suspend fun getChart(
        @Query("jenisPanen") jenisPanen: String,
        @Query("jenis") jenis: String
    ): Response<ChartModel>

}