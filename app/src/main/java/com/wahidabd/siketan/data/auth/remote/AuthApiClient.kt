package com.wahidabd.siketan.data.auth.remote

import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthApiClient {

    @POST("/auth/login")
    fun login(
        @Body data: LoginDataRequest
    ): Single<Response<AuthDataResponse>>

    @POST("auth/register")
    fun register(
        @Body data: RegisterDataRequest
    ): Single<Response<AuthDataResponse>>
}