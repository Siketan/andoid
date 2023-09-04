package com.wahidabd.siketan.data.farm

import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.farm.request.InputTanamanRequest
import com.wahidabd.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import com.wahidabd.siketan.data.farm.model.farm.request.ProductRequest
import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InputTanamanResponse
import com.wahidabd.siketan.data.farm.model.farm.response.TanamanPetaniResponse
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.data.farm.model.journal.JournalResponse
import com.wahidabd.siketan.data.farm.model.journal.PresensiRequest
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.farm.model.request.Chartparam
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
import go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
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
    suspend fun getChart(data: Chartparam): Flow<Resource<ChartModel>>
    suspend fun addTanaman(data: InputTanamanRequest): Flow<Resource<InputTanamanResponse>>
    suspend fun getTanaman(id: Int): Flow<Resource<TanamanPetaniResponse>>
    suspend fun addLaporan(data: LaporanTanamanRequest): Flow<Resource<GenericAddResponse>>
    suspend fun getLaporan(id: Int): Flow<Resource<ReportTanamanResponse>>
}