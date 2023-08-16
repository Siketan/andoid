package com.wahidabd.siketan.data.auth

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.user.DetailPetaniResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import com.wahidabd.siketan.data.auth.model.user.UserEditeRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthRepository : BaseRepository {
    suspend fun login(data: LoginDataRequest): Flow<Resource<AuthDataResponse>>
    suspend fun loginPenyuluh(data: LoginPenyuluhRequest): Flow<Resource<AuthDataResponse>>
    suspend fun register(data: RegisterDataRequest): Flow<Resource<AuthDataResponse>>
    suspend fun getUser(id: Int): Flow<Resource<DetailPetaniResponse>>
    suspend fun editUser(data: UserEditeRequest): Flow<Resource<GenericAddResponse>>
}