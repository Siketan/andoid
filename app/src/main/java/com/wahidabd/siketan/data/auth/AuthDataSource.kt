package com.wahidabd.siketan.data.auth

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import com.wahidabd.siketan.data.auth.remote.AuthApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthDataSource(
    api: AuthApi,
    private val err: ErrorParses
) : AuthRepository {

    override val dbService: LocalDb? = null
    override val webService = api

    override suspend fun login(data: LoginDataRequest): Flow<Resource<AuthDataResponse>> = flow {
        enqueue(data, err::convertGenericError, webService::login, onEmit = { emit(it) })
        debug { "DATA --> $data" }
    }.flowOn(Dispatchers.IO)

    override suspend fun register(data: RegisterDataRequest): Flow<Resource<AuthDataResponse>> =
        flow {
            enqueue(data, err::convertGenericError, webService::register, onEmit = { emit(it) })
        }


}