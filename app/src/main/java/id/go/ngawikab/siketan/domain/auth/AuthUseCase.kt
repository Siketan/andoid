package id.go.ngawikab.siketan.domain.auth

import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.auth.model.FarmerGroup

import id.go.ngawikab.siketan.data.auth.model.user.DetailPetaniResponse
import id.go.ngawikab.siketan.data.auth.model.LoginPenyuluhRequest
import id.go.ngawikab.siketan.data.auth.model.user.UserEditeRequest
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.auth.model.AuthResponse
import id.go.ngawikab.siketan.domain.auth.model.LoginRequest
import id.go.ngawikab.siketan.domain.auth.model.OpsiPenyuluh
import id.go.ngawikab.siketan.domain.auth.model.RegisterRequest
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
    suspend fun penyuluh(): Flow<Resource<List<OpsiPenyuluh>>>
    suspend fun farmerGroups(desa:String): Flow<Resource<List<FarmerGroup>>>

}