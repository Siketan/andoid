package com.wahidabd.siketan.data.auth.remote

import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthApiClient {

    @POST("auth/petani-login")
    suspend fun login(
        @Body data: LoginDataRequest
    ): Response<AuthDataResponse>

    @POST("auth/petani-register")
    suspend fun register(
        @Body data: RegisterDataRequest
    ): Response<AuthDataResponse>
}