package com.wahidabd.siketan.domain.auth

import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.auth.model.user.DetailPetaniResponse
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.data.auth.model.user.UserEditeRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.auth.model.AuthResponse
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthUseCase {
    fun login(data: LoginRequest): Flow<Resource<AuthResponse>>
    fun loginPenyuluh(data: LoginPenyuluhRequest): Flow<Resource<AuthResponse>>
    fun register(data: RegisterRequest): Flow<Resource<AuthResponse>>
    fun getUser(id: Int): Flow<Resource<DetailPetaniResponse>>
    fun editUser(data: UserEditeRequest): Flow<Resource<GenericAddResponse>>
}