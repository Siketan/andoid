package id.go.ngawikab.siketan.domain.farm

import androidx.paging.PagingData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import id.go.ngawikab.siketan.data.farm.FarmRepository
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.LaporanTanamanRequest
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
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
import id.go.ngawikab.siketan.domain.farm.model.request.ProductParam
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import id.go.ngawikab.siketan.domain.farm.model.response.OpsiPetani
import id.go.ngawikab.siketan.domain.farm.model.response.Product
import id.go.ngawikab.siketan.domain.farm.model.response.toDomain
import id.go.ngawikab.siketan.domain.farm.model.response.todDomain
import id.go.ngawikab.siketan.domain.farm.model.toRequest
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
                return data.data.map {
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

    override fun getChart(data: Chartparam): Flow<Resource<ChartResponse>> {
        return object : InternetBoundResource<ChartResponse, ChartResponse>() {
            override suspend fun createCall(): Flow<Resource<ChartResponse>> {
                return repository.getChart(data)
            }

            override suspend fun saveCallRequest(data: ChartResponse): ChartResponse {
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

    override fun getTanaman(id: Int): Flow<PagingData<PlantFarmerData>> {
        return repository.getTanaman(id)
    }
    override suspend fun getPetani(id:Int): Flow<Resource<List<OpsiPetani>>> =
        object : InternetBoundResource<List<OpsiPetani>, OpsiPetaniResponse>() {
            override suspend fun createCall(): Flow<Resource<OpsiPetaniResponse>> {
                return repository.getPetani(id)
            }

            override suspend fun saveCallRequest(data: OpsiPetaniResponse): List<OpsiPetani> {
                return data.petanis.map {
                    it.todDomain()
                }
            }

        }.asFlow()

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

    override fun getProductsbyPaging(): Flow<PagingData<ProductResponse>> {
        return repository.getProductsbyPaging()
    }

    override fun getPetanibyPaging(id: Int): Flow<PagingData<Petani>> {
        return repository.getPetanibyPaging(id)
    }

}