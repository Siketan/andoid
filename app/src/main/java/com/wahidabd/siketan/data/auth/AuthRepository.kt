package com.wahidabd.siketan.data.auth

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthRepository : BaseRepository {
    suspend fun login(data: LoginDataRequest): Flow<Resource<AuthDataResponse>>
    suspend fun register(data: RegisterDataRequest): Flow<Resource<AuthDataResponse>>
}