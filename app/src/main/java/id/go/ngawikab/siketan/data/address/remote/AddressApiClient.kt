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

    @GET("kecamatan?id_kota=3521")
    suspend fun kecamatan(): Response<ResponseAddress>

    @GET("kelurahan")
    suspend fun kelurahan(
        @Query("id_kecamatan") id_kecamatan: Long
    ): Response<ResponseAddress>
}