package id.go.ngawikab.siketan.data.address.remote

import id.go.ngawikab.siketan.data.address.response.ResponseAddress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


interface AddressApiClient {

    @GET("wilayah/kecamatan")
    suspend fun kecamatan(): Response<ResponseAddress>

    @GET("wilayah/desa")
    suspend fun kelurahan(
        @Query("kecamatanId") kecamatanId: Long
    ): Response<ResponseAddress>
}