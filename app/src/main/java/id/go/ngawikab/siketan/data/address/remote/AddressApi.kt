package id.go.ngawikab.siketan.data.address.remote

import com.wahidabd.library.data.WebApi
import id.go.ngawikab.siketan.data.address.response.ResponseAddress
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