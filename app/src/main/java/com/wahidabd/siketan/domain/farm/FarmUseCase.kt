package com.wahidabd.siketan.domain.farm

import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.domain.farm.model.InfoTani
import com.wahidabd.siketan.domain.farm.model.Product
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


interface FarmUseCase {
    fun getInfoTani(): Flow<Resource<List<InfoTani>>>
    fun getProduct(): Flow<Resource<List<Product>>>
}