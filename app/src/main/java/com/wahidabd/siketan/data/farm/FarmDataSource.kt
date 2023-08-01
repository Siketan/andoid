package com.wahidabd.siketan.data.farm

import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.siketan.data.farm.model.farm.request.ProductRequest
import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.data.farm.model.journal.JournalResponse
import com.wahidabd.siketan.data.farm.model.journal.PresensiRequest
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.data.farm.remote.FarmApi
import com.wahidabd.siketan.domain.farm.model.request.Chartparam
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
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

    override suspend fun getInfoTani(): Flow<Resource<InfoTaniDataResponse<InfoTaniResponse>>> =
        flow {
            enqueue(err::convertGenericError, webService::getInfoTani, onEmit = { emit(it) })
        }.flowOn(Dispatchers.IO)

    override suspend fun getEvent(): Flow<Resource<InfoTaniDataResponse<EventTaniResponse>>> =
        flow {
            enqueue(err::convertGenericError, webService::getEvent, onEmit = { emit(it) })
        }.flowOn(Dispatchers.IO)


    override suspend fun getProduct(): Flow<Resource<ProductDataResponse>> = flow {
        enqueue(err::convertGenericError, webService::getProduct, onEmit = { emit(it) })
    }.flowOn(Dispatchers.IO)

    override suspend fun addProduct(data: ProductRequest): Flow<Resource<GenericAddResponse>> =
        flow {
            enqueue(
                data.toBody(),
                err::convertGenericError,
                webService::postStore,
                onEmit = { emit(it) })
        }.flowOn(Dispatchers.IO)

    override suspend fun getJournal(): Flow<Resource<JournalResponse>> = flow {
        enqueue(err::convertGenericError, webService::getJournal, onEmit = { emit(it) })
    }.flowOn(Dispatchers.IO)

    override suspend fun addJournal(data: JournalAddRequest): Flow<Resource<GenericAddResponse>> =
        flow {
            enqueue(
                data.toRequestBody(),
                err::convertGenericError,
                webService::addJournal,
                onEmit = { emit(it) })
        }.flowOn(Dispatchers.IO)

    override suspend fun addPresensi(data: PresensiRequest): Flow<Resource<GenericAddResponse>> =
        flow {
            enqueue(
                data.toRequestBody(),
                err::convertGenericError,
                webService::addPresensi,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun getChart(data: Chartparam): Flow<Resource<ChartModel>> =
        flow {
            enqueue(
                data.jenisPanen.type,
                data.jenis.type,
                err::convertGenericError,
                webService::getChart,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

}