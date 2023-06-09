package com.wahidabd.siketan.data.auth.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthApi(private val client: AuthApiClient) : WebApi, AuthApiClient {
    override fun login(data: LoginDataRequest): Single<Response<AuthDataResponse>> {
        return client.login(data)
    }

    override fun register(data: RegisterDataRequest): Single<Response<AuthDataResponse>> {
        return client.register(data)
    }
}