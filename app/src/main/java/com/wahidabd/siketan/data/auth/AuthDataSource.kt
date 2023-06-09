package com.wahidabd.siketan.data.auth

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.WebApi
import com.wahidabd.library.utils.rx.operators.getSingleApiError
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import com.wahidabd.siketan.data.auth.remote.AuthApi
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthDataSource(
    api: AuthApi
) : AuthRepository {

    override val dbService: LocalDb? = null
    override val webService = api

    override fun login(data: LoginDataRequest): Single<AuthDataResponse> {
        return webService.login(data)
            .lift(getSingleApiError())
            .map { it }
    }

    override fun register(data: RegisterDataRequest): Single<AuthDataResponse> {
        return webService.register(data)
            .lift(getSingleApiError())
            .map { it }
    }


}