package com.wahidabd.siketan.data.address

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.address.response.ResponseAddress
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


interface AddressRepository : BaseRepository {

    suspend fun kecamatan(): Flow<Resource<ResponseAddress>>
    suspend fun kelurahan(id: Long): Flow<Resource<ResponseAddress>>

}