package com.wahidabd.siketan.data.auth.remote

import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.user.DetailPetaniResponse
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthApiClient {

    @POST("auth/petani-login")
    suspend fun login(
        @Body data: LoginDataRequest
    ): Response<AuthDataResponse>

    @POST("auth/petani-login")
    suspend fun loginPenyuluh(
        @Body data: LoginPenyuluhRequest
    ): Response<AuthDataResponse>

    @POST("auth/petani-register")
    suspend fun register(
        @Body data: RegisterDataRequest
    ): Response<AuthDataResponse>

    @GET("daftar-tani/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): Response<DetailPetaniResponse>

    @PUT("daftar-tani/{id}")
    suspend fun editUser(
        @Path("id") id: Int,
        @Body body: MultipartBody
    ): Response<GenericAddResponse>
}