package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import retrofit2.Response


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


class FarmApi(private val api: FarmApiClient) : WebApi, FarmApiClient {
    override suspend fun getInfoTani(): Response<InfoTaniDataResponse<InfoTaniResponse>> {
        return api.getInfoTani()
    }

    override suspend fun getEvent(): Response<InfoTaniDataResponse<EventTaniResponse>> {
        return api.getEvent()
    }

    override suspend fun getProduct(): Response<ProductDataResponse> {
        return api.getProduct()
    }
}