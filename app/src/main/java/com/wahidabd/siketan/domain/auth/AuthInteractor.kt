package com.wahidabd.siketan.domain.auth

import com.wahidabd.siketan.data.auth.AuthRepository
import com.wahidabd.siketan.domain.auth.model.AuthResponse
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import com.wahidabd.siketan.domain.auth.model.toData
import com.wahidabd.siketan.domain.auth.model.toDomain
import io.reactivex.rxjava3.core.Single


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthInteractor(private val repository: AuthRepository) : AuthUseCase {
    override fun login(data: LoginRequest): Single<AuthResponse> {
        return repository.login(data.toData()).map {
            it.toDomain()
        }
    }

    override fun register(data: RegisterRequest): Single<AuthResponse> {
        return repository.register(data.toData()).map {
            it.toDomain()
        }
    }
}