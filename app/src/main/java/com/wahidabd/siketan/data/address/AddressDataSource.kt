package com.wahidabd.siketan.data.address

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.siketan.data.address.remote.AddressApi
import com.wahidabd.siketan.data.address.response.ResponseAddress
import com.wahidabd.siketan.domain.address.model.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


class AddressDataSource(
    api: AddressApi,
    private val err: ErrorParses
) : AddressRepository {

    override val dbService: LocalDb? = null
    override val webService = api


    override suspend fun kecamatan(): Flow<Resource<ResponseAddress>> = flow {
        enqueue(err::convertGenericError, webService::kecamatan, onEmit = { emit(it) })
    }.flowOn(Dispatchers.IO)

    override suspend fun kelurahan(id: Long): Flow<Resource<ResponseAddress>> = flow {
        enqueue(id, err::convertGenericError, webService::kelurahan, onEmit = { emit(it) })
    }.flowOn(Dispatchers.IO)

}