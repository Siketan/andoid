package id.go.ngawikab.siketan.data.auth.remote

import id.go.ngawikab.siketan.data.auth.model.LoginDataRequest
import id.go.ngawikab.siketan.data.auth.model.AuthDataResponse
import id.go.ngawikab.siketan.data.auth.model.FarmerGroupsResponse
import id.go.ngawikab.siketan.data.auth.model.user.DetailPetaniResponse
import id.go.ngawikab.siketan.data.auth.model.LoginPenyuluhRequest
import id.go.ngawikab.siketan.data.auth.model.RegisterDataRequest
import id.go.ngawikab.siketan.data.auth.model.user.DetailUserProfileResponse
import id.go.ngawikab.siketan.data.auth.model.user.OpsiPenyuluhResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


interface AuthApiClient {

    @POST("auth/petani-login")
    suspend fun login(
        @Body data: LoginDataRequest
    ): Response<AuthDataResponse>

    @POST("auth/petani-login")
    suspend fun loginPenyuluh(
        @Body data: LoginPenyuluhRequest
    ): Response<AuthDataResponse>

    @POST("auth/petani-register")
    suspend fun register(
        @Body data: RegisterDataRequest
    ): Response<AuthDataResponse>

    @GET("auth/profile")
    suspend fun getUserProfile(
    ): Response<DetailUserProfileResponse>

    @GET("daftar-tani/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): Response<DetailPetaniResponse>

    @POST("auth/updateprofile")
    suspend fun editUser(
        @Body body: MultipartBody
    ): Response<GenericAddResponse>

    @GET("opsi-penyuluh")
    suspend fun getPenyuluh(
    ): Response<OpsiPenyuluhResponse>

    @GET("/kelompok-tani/desa/{desa}")
    suspend fun getKelompokTani(
        @Path("desa") desa: String,
    ): Response<FarmerGroupsResponse>
}