package com.wahidabd.siketan.presentation.store.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.store.response.ProductAddResponse
import com.wahidabd.siketan.domain.farm.FarmUseCase
import com.wahidabd.siketan.domain.farm.model.request.ProductParam
import com.wahidabd.siketan.domain.farm.model.response.Product
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty


/**
 * Created by Wahid on 6/17/2023.
 * Github github.com/wahidabd.
 */


class StoreViewModel(
    private val useCase: FarmUseCase
) : ViewModel() {

    private val _products = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>> get() = _products

    private val _addProduct = MutableLiveData<Resource<ProductAddResponse>>()
    val addProduct: LiveData<Resource<ProductAddResponse>> get() = _addProduct

    fun product() {
        useCase.getProduct()
            .onEach { _products.value = it }
            .onEmpty { _products.value = Resource.empty() }
            .launchIn(viewModelScope)
    }

    fun addProduct(data: ProductParam) {
        useCase.addProduct(data)
            .onEach { _addProduct.value = it }
            .launchIn(viewModelScope)
    }

}