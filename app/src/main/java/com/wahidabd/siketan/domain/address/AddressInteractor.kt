package com.wahidabd.siketan.domain.address

import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.address.AddressDataSource
import com.wahidabd.siketan.data.address.AddressRepository
import com.wahidabd.siketan.data.address.response.ResponseAddress
import com.wahidabd.siketan.domain.address.model.Address
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