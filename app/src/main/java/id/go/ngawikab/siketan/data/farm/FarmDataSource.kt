package id.go.ngawikab.siketan.data.farm

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.library.data.LocalDb
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.ProductRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.ChartResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.EventTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InputTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.OpsiPetaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.journal.JournalAddRequest
import id.go.ngawikab.siketan.data.farm.model.journal.JournalResponse
import id.go.ngawikab.siketan.data.farm.model.journal.PresensiRequest
import id.go.ngawikab.siketan.data.farm.model.store.ProductDataResponse
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.data.farm.remote.FarmApi
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
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

    override suspend fun getChart(data: Chartparam): Flow<Resource<ChartResponse>> =
        flow {
            enqueue(
                data.id ?: 0,
                data.musim.type,
                data.jenis.type,
                err::convertGenericError,
                webService::getChart,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun addTanaman(data: InputTanamanRequest): Flow<Resource<InputTanamanResponse>> =
        flow {
            enqueue(
                data,
                err::convertGenericError,
                webService::addTanaman,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)


    override suspend fun getPetani(id:Int): Flow<Resource<OpsiPetaniResponse>> =
        flow {
            enqueue(
                id,
                err::convertGenericError,
                webService::getPetani,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun addLaporan(data: LaporanTanamanRequest): Flow<Resource<GenericAddResponse>> =
        flow {
            enqueue(
                data.toBody(),
                err::convertGenericError,
                webService::addLaporan,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun getLaporan(id: Int): Flow<Resource<ReportTanamanResponse>> =
        flow {
            enqueue(
                id,
                err::convertGenericError,
                webService::getLaporan,
                onEmit = { emit(it) }
            )
        }.flowOn(Dispatchers.IO)

    override fun getProductsbyPaging(
    ): Flow<PagingData<ProductResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = {StorePagingSource(webService)}
        ).flow

    override fun getTanaman(
        id: Int,
    ): Flow<PagingData<PlantFarmerData>> =
        Pager(
             config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = {TanamanPagingSource(webService,id)}
        ).flow

    override fun getPetanibyPaging(
        id:Int,
    ): Flow<PagingData<Petani>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = {PetaniPagingSource(webService, id)}
        ).flow
}