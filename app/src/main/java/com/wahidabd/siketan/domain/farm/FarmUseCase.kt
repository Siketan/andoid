package com.wahidabd.siketan.domain.farm

import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.farm.request.InputTanamanRequest
import com.wahidabd.siketan.data.farm.model.farm.response.InputTanamanResponse
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.data.farm.model.journal.JournalResponse
import com.wahidabd.siketan.data.farm.model.journal.PresensiRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.farm.model.request.Chartparam
import com.wahidabd.siketan.domain.farm.model.request.ProductParam
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
import com.wahidabd.siketan.domain.farm.model.response.EventTani
import com.wahidabd.siketan.domain.farm.model.response.InfoTani
import com.wahidabd.siketan.domain.farm.model.response.Product
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
    fun getChart(data: Chartparam): Flow<Resource<ChartModel>>
    fun addTanaman(data: InputTanamanRequest): Flow<Resource<InputTanamanResponse>>
}