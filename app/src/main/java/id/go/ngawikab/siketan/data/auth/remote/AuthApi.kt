package id.go.ngawikab.siketan.data.auth.remote

import com.wahidabd.library.data.WebApi
import id.go.ngawikab.siketan.data.auth.model.AuthDataResponse
import id.go.ngawikab.siketan.data.auth.model.FarmerGroupsResponse
import id.go.ngawikab.siketan.data.auth.model.user.DetailPetaniResponse
import id.go.ngawikab.siketan.data.auth.model.LoginDataRequest
import id.go.ngawikab.siketan.data.auth.model.LoginPenyuluhRequest
import id.go.ngawikab.siketan.data.auth.model.RegisterDataRequest
import id.go.ngawikab.siketan.data.auth.model.user.DetailUserProfileResponse
import id.go.ngawikab.siketan.data.auth.model.user.OpsiPenyuluhResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
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

    override suspend  fun getUserProfile(): Response<DetailUserProfileResponse> {
        return api.getUserProfile()
    }

    override suspend fun getUser(id: Int): Response<DetailPetaniResponse> {
        return api.getUser(id)
    }

    override suspend fun editUser(body: MultipartBody): Response<GenericAddResponse> {
        return api.editUser(body)
    }

    override suspend fun getPenyuluh(): Response<OpsiPenyuluhResponse> {
        return api.getPenyuluh()
    }

    override suspend fun getKelompokTani(desa: String): Response<FarmerGroupsResponse> {
        return api.getKelompokTani(desa)
    }

}