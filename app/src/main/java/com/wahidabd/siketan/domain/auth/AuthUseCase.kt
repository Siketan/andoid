package com.wahidabd.siketan.domain.auth

import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
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
}