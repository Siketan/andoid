package com.wahidabd.siketan.domain.auth

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.siketan.data.auth.AuthRepository
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.user.DetailPetaniResponse
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.data.auth.model.user.UserEditeRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
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

    override fun loginPenyuluh(data: LoginPenyuluhRequest): Flow<Resource<AuthResponse>> =
        object : InternetBoundResource<AuthResponse, AuthDataResponse>() {
            override suspend fun createCall(): Flow<Resource<AuthDataResponse>> {
                return repository.loginPenyuluh(data)
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

    override fun getUser(id: Int): Flow<Resource<DetailPetaniResponse>> =
        object : InternetBoundResource<DetailPetaniResponse, DetailPetaniResponse>() {
            override suspend fun createCall(): Flow<Resource<DetailPetaniResponse>> {
                return repository.getUser(id)
            }

            override suspend fun saveCallRequest(data: DetailPetaniResponse): DetailPetaniResponse {
                return data
            }
        }.asFlow()

    override fun editUser(data: UserEditeRequest): Flow<Resource<GenericAddResponse>> =
        object : InternetBoundResource<GenericAddResponse, GenericAddResponse>() {
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.editUser(data)
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }
        }.asFlow()

}