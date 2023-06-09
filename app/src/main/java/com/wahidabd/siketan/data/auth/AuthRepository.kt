package com.wahidabd.siketan.data.auth

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthRepository: BaseRepository {
    fun login(data: LoginDataRequest): Single<AuthDataResponse>
    fun register(data: RegisterDataRequest): Single<AuthDataResponse>
}