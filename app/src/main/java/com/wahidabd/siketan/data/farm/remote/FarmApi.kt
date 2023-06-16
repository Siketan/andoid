package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.farm.model.InfoTaniDataResponse
import retrofit2.Response


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


class FarmApi(private val api: FarmApiClient) : WebApi, FarmApiClient {
    override suspend fun getInfoTani(): Response<InfoTaniDataResponse> {
        return api.getInfoTani()
    }
}