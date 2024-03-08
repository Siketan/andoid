package id.go.ngawikab.siketan.domain.farm

import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.ChartResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InputTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerResponse
import id.go.ngawikab.siketan.data.farm.model.journal.JournalAddRequest
import id.go.ngawikab.siketan.data.farm.model.journal.JournalResponse
import id.go.ngawikab.siketan.data.farm.model.journal.PresensiRequest
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
import id.go.ngawikab.siketan.domain.farm.model.request.ProductParam
import id.go.ngawikab.siketan.domain.farm.model.response.ChartModel
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import id.go.ngawikab.siketan.domain.farm.model.response.Product
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


interface FarmUseCase {
    fun getInfoTani(): Flow<Resource<List<InfoTani>>>
    fun getEvent(): Flow<Resource<List<EventTani>>>
    fun getProduct(): Flow<Resource<List<Product>>>
    fun addProduct(data: ProductParam): Flow<Resource<GenericAddResponse>>
    fun getJournal(): Flow<Resource<JournalResponse>>
    fun addJournal(data: JournalAddRequest): Flow<Resource<GenericAddResponse>>
    fun addPresensi(data: PresensiRequest): Flow<Resource<GenericAddResponse>>
    fun getChart(data: Chartparam): Flow<Resource<ChartResponse>>
    fun addTanaman(data: InputTanamanRequest): Flow<Resource<InputTanamanResponse>>
    fun getTanaman(id: Int,page:Int,limit:Int): Flow<Resource<PlantFarmerResponse>>
    fun addLaporan(data: LaporanTanamanRequest): Flow<Resource<GenericAddResponse>>
    fun getLaporan(id: Int): Flow<Resource<ReportTanamanResponse>>
}