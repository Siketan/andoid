package com.wahidabd.siketan.data.auth.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.user.DetailPetaniResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import okhttp3.MultipartBody
import retrofit2.Response


/**
 * Created by Wahid on 6/11/2023.
 * Github github.com/wahidabd.
 */


class AuthApi (private val api: AuthApiClient) : WebApi, AuthApiClient {

    override suspend  fun login(data: LoginDataRequest): Response<AuthDataResponse> {
        return api.login(data)
    }

    override suspend fun loginPenyuluh(data: LoginPenyuluhRequest): Response<AuthDataResponse> {
        return api.loginPenyuluh(data)
    }

    override suspend  fun register(data: RegisterDataRequest): Response<AuthDataResponse> {
        return api.register(data)
    }

    override suspend fun getUser(id: Int): Response<DetailPetaniResponse> {
        return api.getUser(id)
    }

    override suspend fun editUser(id: Int, body: MultipartBody): Response<GenericAddResponse> {
        return api.editUser(id, body)
    }
}