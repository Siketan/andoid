package id.go.ngawikab.siketan.data.farm

import androidx.paging.Pager
import androidx.paging.PagingData
import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.ProductRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.ChartResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.EventTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InputTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerResponse
import id.go.ngawikab.siketan.data.farm.model.journal.JournalAddRequest
import id.go.ngawikab.siketan.data.farm.model.journal.JournalResponse
import id.go.ngawikab.siketan.data.farm.model.journal.PresensiRequest
import id.go.ngawikab.siketan.data.farm.model.store.ProductDataResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
import id.go.ngawikab.siketan.domain.farm.model.response.ChartModel
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


interface FarmRepository : BaseRepository {

    suspend fun getInfoTani(): Flow<Resource<InfoTaniDataResponse<InfoTaniResponse>>>
    suspend fun getEvent(): Flow<Resource<InfoTaniDataResponse<EventTaniResponse>>>
    suspend fun getProduct(): Flow<Resource<ProductDataResponse>>
    suspend fun addProduct(data: ProductRequest): Flow<Resource<GenericAddResponse>>
    suspend fun getJournal(): Flow<Resource<JournalResponse>>
    suspend fun addJournal(data: JournalAddRequest): Flow<Resource<GenericAddResponse>>
    suspend fun addPresensi(data: PresensiRequest): Flow<Resource<GenericAddResponse>>
    suspend fun getChart(data: Chartparam): Flow<Resource<ChartResponse>>
    suspend fun addTanaman(data: InputTanamanRequest): Flow<Resource<InputTanamanResponse>>
    suspend fun getTanaman(id: Int,page:Int,limit:Int): Flow<Resource<PlantFarmerResponse>>
    suspend fun addLaporan(data: LaporanTanamanRequest): Flow<Resource<GenericAddResponse>>
    suspend fun getLaporan(id: Int): Flow<Resource<ReportTanamanResponse>>
    fun getProductsbyPaging():Flow<PagingData<ProductResponse>>
}