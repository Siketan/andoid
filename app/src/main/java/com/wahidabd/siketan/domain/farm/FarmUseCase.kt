package com.wahidabd.siketan.domain.farm

import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.farm.request.ProductRequest
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.data.farm.model.store.response.ProductAddResponse
import com.wahidabd.siketan.domain.farm.model.request.ProductParam
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
    fun addProduct(data: ProductParam): Flow<Resource<ProductAddResponse>>
    fun addJournal(data: JournalAddRequest): Flow<Resource<ProductAddResponse>>
}