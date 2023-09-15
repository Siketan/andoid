package id.go.ngawikab.siketan.domain.farm

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import id.go.ngawikab.siketan.data.farm.FarmRepository
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.EventTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InputTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.TanamanPetaniResponse
import id.go.ngawikab.siketan.data.farm.model.journal.JournalAddRequest
import id.go.ngawikab.siketan.data.farm.model.journal.JournalResponse
import id.go.ngawikab.siketan.data.farm.model.journal.PresensiRequest
import id.go.ngawikab.siketan.data.farm.model.store.ProductDataResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
import id.go.ngawikab.siketan.domain.farm.model.request.ProductParam
import id.go.ngawikab.siketan.domain.farm.model.response.ChartModel
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import id.go.ngawikab.siketan.domain.farm.model.response.Product
import id.go.ngawikab.siketan.domain.farm.model.response.toDomain
import id.go.ngawikab.siketan.domain.farm.model.toRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


class FarmInteractor(private val repository: FarmRepository) : FarmUseCase {

    override fun getInfoTani(): Flow<Resource<List<InfoTani>>> =
        object : InternetBoundResource<List<InfoTani>, InfoTaniDataResponse<InfoTaniResponse>>() {
            override suspend fun createCall(): Flow<Resource<InfoTaniDataResponse<InfoTaniResponse>>> {
                return repository.getInfoTani()
            }

            override suspend fun saveCallRequest(data: InfoTaniDataResponse<InfoTaniResponse>): List<InfoTani> {
                return data.infotani.map {
                    it.toDomain()
                }
            }
        }.asFlow()

    override fun getEvent(): Flow<Resource<List<EventTani>>> =
        object : InternetBoundResource<List<EventTani>, InfoTaniDataResponse<EventTaniResponse>>() {
            override suspend fun createCall(): Flow<Resource<InfoTaniDataResponse<EventTaniResponse>>> {
                return repository.getEvent()
            }

            override suspend fun saveCallRequest(data: InfoTaniDataResponse<EventTaniResponse>): List<EventTani> {
                return data.infotani.map {
                    it.toDomain()
                }
            }

        }.asFlow()


    override fun getProduct(): Flow<Resource<List<Product>>> =
        object : InternetBoundResource<List<Product>, ProductDataResponse>() {
            override suspend fun createCall(): Flow<Resource<ProductDataResponse>> {
                return repository.getProduct()
            }

            override suspend fun saveCallRequest(data: ProductDataResponse): List<Product> {
                return data.productPetani.map {
                    it.toDomain()
                }
            }

        }.asFlow()

    override fun addProduct(data: ProductParam): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>(){
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addProduct(data.toRequest())
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }

        }.asFlow()
    }

    override fun getJournal(): Flow<Resource<JournalResponse>> {
        return object : InternetBoundResource<JournalResponse, JournalResponse>(){
            override suspend fun createCall(): Flow<Resource<JournalResponse>> {
                return repository.getJournal()
            }

            override suspend fun saveCallRequest(data: JournalResponse): JournalResponse {
                return data
            }
        }.asFlow()
    }

    override fun addJournal(data: JournalAddRequest): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>() {
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addJournal(data)
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }
        }.asFlow()
    }

    override fun addPresensi(data: PresensiRequest): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>(){
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addPresensi(data)
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }

        }.asFlow()
    }

    override fun getChart(data: Chartparam): Flow<Resource<ChartModel>> {
        return object : InternetBoundResource<ChartModel, ChartModel>() {
            override suspend fun createCall(): Flow<Resource<ChartModel>> {
                return repository.getChart(data)
            }

            override suspend fun saveCallRequest(data: ChartModel): ChartModel {
                return data
            }

        }.asFlow()
    }

    override fun addTanaman(data: InputTanamanRequest): Flow<Resource<InputTanamanResponse>> {
        return object : InternetBoundResource<InputTanamanResponse, InputTanamanResponse>(){
            override suspend fun createCall(): Flow<Resource<InputTanamanResponse>> {
                return repository.addTanaman(data)
            }

            override suspend fun saveCallRequest(data: InputTanamanResponse): InputTanamanResponse {
                return data
            }
        }.asFlow()
    }

    override fun getTanaman(id: Int): Flow<Resource<TanamanPetaniResponse>> {
        return object : InternetBoundResource<TanamanPetaniResponse, TanamanPetaniResponse>(){
            override suspend fun createCall(): Flow<Resource<TanamanPetaniResponse>> {
                return repository.getTanaman(id)
            }

            override suspend fun saveCallRequest(data: TanamanPetaniResponse): TanamanPetaniResponse {
                return data
            }

        }.asFlow()
    }

    override fun addLaporan(data: LaporanTanamanRequest): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>(){
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addLaporan(data)
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }

        }.asFlow()
    }

    override fun getLaporan(id: Int): Flow<Resource<ReportTanamanResponse>> {
        return object : InternetBoundResource<ReportTanamanResponse, ReportTanamanResponse>(){
            override suspend fun createCall(): Flow<Resource<ReportTanamanResponse>> {
                return repository.getLaporan(id)
            }

            override suspend fun saveCallRequest(data: ReportTanamanResponse): ReportTanamanResponse {
                return data
            }
        }.asFlow()
    }

}