package com.wahidabd.siketan.domain.auth

import com.wahidabd.siketan.domain.auth.model.AuthResponse
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthUseCase {
    fun login(data: LoginRequest): Single<AuthResponse>
    fun register(data: RegisterRequest): Single<AuthResponse>
}