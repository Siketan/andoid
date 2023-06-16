package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.siketan.data.farm.model.InfoTaniDataResponse
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


interface FarmApiClient {

    @GET("info-tani")
    suspend fun getInfoTani(): Response<InfoTaniDataResponse>

}