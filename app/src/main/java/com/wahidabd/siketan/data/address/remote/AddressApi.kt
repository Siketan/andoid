package com.wahidabd.siketan.data.address.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.address.response.ResponseAddress
import com.wahidabd.siketan.domain.address.model.Address
import retrofit2.Response


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


class AddressApi(private val api: AddressApiClient) : WebApi, AddressApiClient {

    override suspend fun kecamatan(): Response<ResponseAddress> {
        return api.kecamatan()
    }

    override suspend fun kelurahan(id: Long): Response<ResponseAddress> {
        return api.kelurahan(id)
    }
}