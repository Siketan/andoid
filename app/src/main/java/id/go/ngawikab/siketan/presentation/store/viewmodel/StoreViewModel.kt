package id.go.ngawikab.siketan.presentation.store.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.FarmUseCase
import id.go.ngawikab.siketan.domain.farm.model.request.ProductParam
import id.go.ngawikab.siketan.domain.farm.model.response.Product
import kotlinx.coroutines.flow.distinctUntilChanged
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

    val products = useCase.getProductsbyPaging().distinctUntilChanged()

    private val _productsPaging = MutableLiveData<PagingData<ProductResponse>>()
    val productsPaging: LiveData<PagingData<ProductResponse>> get() = _productsPaging

    private val _addProduct = MutableLiveData<Resource<GenericAddResponse>>()
    val addProduct: LiveData<Resource<GenericAddResponse>> get() = _addProduct

    init {
        getProductsPaging()
    }

    fun getProductsPaging() {
        useCase.getProductsbyPaging()
            .onEach { _productsPaging.value = it }
            .launchIn(viewModelScope)
    }

    fun addProduct(data: ProductParam) {
        useCase.addProduct(data)
            .onEach { _addProduct.value = it }
            .launchIn(viewModelScope)
    }

}