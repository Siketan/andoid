package id.go.ngawikab.siketan.domain.address

import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.address.AddressRepository
import id.go.ngawikab.siketan.data.address.response.ResponseAddress
import kotlinx.coroutines.flow.Flow

/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


class AddressInteractor(
    private val repository: AddressRepository
) : AddressUseCase {

    override suspend fun kecamatan(): Flow<Resource<ResponseAddress>> {
        return repository.kecamatan()
    }

    override suspend fun kelurahan(id: Long): Flow<Resource<ResponseAddress>> {
        return repository.kelurahan(id)
    }
}