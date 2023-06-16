package com.wahidabd.siketan.data.farm

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.siketan.data.farm.model.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.remote.FarmApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


class FarmDataSource(
    api: FarmApi,
    private val err: ErrorParses
) : FarmRepository {

    override val dbService: LocalDb? = null
    override val webService = api

    override suspend fun getInfoTani(): Flow<Resource<InfoTaniDataResponse>> = flow {
        enqueue(err::convertGenericError, webService::getInfoTani, onEmit = { emit(it) })
    }.flowOn(Dispatchers.IO)

}