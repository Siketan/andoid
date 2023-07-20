package com.wahidabd.siketan.presentation.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.address.response.ResponseAddress
import com.wahidabd.siketan.domain.address.AddressUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


class AddressViewModel(
    private val useCase: AddressUseCase
) : ViewModel() {

    private val _kecamatan = MutableLiveData<Resource<ResponseAddress>>()
    val kecamatan: LiveData<Resource<ResponseAddress>> get() = _kecamatan

    private val _kelurahan = MutableLiveData<Resource<ResponseAddress>>()
    val kelurahan: LiveData<Resource<ResponseAddress>> get() = _kelurahan


    init {
        kecamatan()
    }

    private fun kecamatan() {
        viewModelScope.launch {
            useCase.kecamatan()
                .onEach { _kecamatan.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun kelurahan(id: Long) {
        viewModelScope.launch {
            useCase.kelurahan(id)
                .onEach { _kelurahan.value = it }
                .launchIn(viewModelScope)
        }
    }


}