package id.go.ngawikab.siketan.data.auth

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.auth.model.AuthDataResponse
import id.go.ngawikab.siketan.data.auth.model.FarmerGroupsResponse
import id.go.ngawikab.siketan.data.auth.model.user.DetailPetaniResponse
import id.go.ngawikab.siketan.data.auth.model.LoginDataRequest
import id.go.ngawikab.siketan.data.auth.model.LoginPenyuluhRequest
import id.go.ngawikab.siketan.data.auth.model.RegisterDataRequest
import id.go.ngawikab.siketan.data.auth.model.user.OpsiPenyuluhResponse
import id.go.ngawikab.siketan.data.auth.model.user.UserEditeRequest
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.auth.model.OpsiPenyuluh
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
    suspend fun getPenyuluh(): Flow<Resource<OpsiPenyuluhResponse>>
    suspend fun getFarmerGroups(desa:String): Flow<Resource<FarmerGroupsResponse>>
}