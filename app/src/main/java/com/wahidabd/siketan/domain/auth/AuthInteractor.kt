package com.wahidabd.siketan.domain.auth

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.siketan.data.auth.AuthRepository
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.domain.auth.model.AuthResponse
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import com.wahidabd.siketan.domain.auth.model.toData
import com.wahidabd.siketan.domain.auth.model.toDomain
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthInteractor(private val repository: AuthRepository) : AuthUseCase {

    override fun login(data: LoginRequest): Flow<Resource<AuthResponse>> =
        object : InternetBoundResource<AuthResponse, AuthDataResponse>() {
            override suspend fun createCall(): Flow<Resource<AuthDataResponse>> {
                return repository.login(data.toData())
            }

            override suspend fun saveCallRequest(data: AuthDataResponse): AuthResponse {
                return data.toDomain()
            }

        }.asFlow()

    override fun register(data: RegisterRequest): Flow<Resource<AuthResponse>> =
        object : InternetBoundResource<AuthResponse, AuthDataResponse>() {
            override suspend fun createCall(): Flow<Resource<AuthDataResponse>> {
                return repository.register(data.toData())
            }


            override suspend fun saveCallRequest(data: AuthDataResponse): AuthResponse {
                return data.toDomain()
            }

        }.asFlow()

}