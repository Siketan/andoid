package com.wahidabd.siketan.presentation.report.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.farm.request.InputTanamanRequest
import com.wahidabd.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import com.wahidabd.siketan.data.farm.model.farm.response.InputTanamanResponse
import com.wahidabd.siketan.data.farm.model.farm.response.TanamanPetaniResponse
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.farm.FarmUseCase
import com.wahidabd.siketan.domain.farm.model.request.Chartparam
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
import go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 8/2/2023.
 * Github github.com/wahidabd.
 */


class ReportViewModel(private val useCase: FarmUseCase) : ViewModel() {

    private val _chart = MutableLiveData<Resource<ChartModel>>()
    val chart: LiveData<Resource<ChartModel>> get() = _chart

    private val _addTanaman = MutableLiveData<Resource<InputTanamanResponse>>()
    val addTanaman: LiveData<Resource<InputTanamanResponse>> get() = _addTanaman

    private val _getTanaman = MutableLiveData<Resource<TanamanPetaniResponse>>()
    val getTanaman: LiveData<Resource<TanamanPetaniResponse>> get() = _getTanaman

    private val _addLaporan = MutableLiveData<Resource<GenericAddResponse>>()
    val addLaporan: LiveData<Resource<GenericAddResponse>> get() = _addLaporan

    private val _getLaporan = MutableLiveData<Resource<ReportTanamanResponse>>()
    val getLaporan: LiveData<Resource<ReportTanamanResponse>> get() = _getLaporan

    fun getChart(data: Chartparam) {
        useCase.getChart(data)
            .onEach { _chart.value = it }
            .launchIn(viewModelScope)
    }


    fun addTanaman(data: InputTanamanRequest){
        useCase.addTanaman(data)
            .onEach { _addTanaman.value = it }
            .launchIn(viewModelScope)
    }

    fun getTanaman(id: Int){
        useCase.getTanaman(id)
            .onEach { _getTanaman.value = it }
            .launchIn(viewModelScope)
    }

    fun addLaporan(data: LaporanTanamanRequest){
        useCase.addLaporan(data)
            .onEach { _addLaporan.value = it }
            .launchIn(viewModelScope)
    }

    fun getLaporan(id: Int){
        useCase.getLaporan(id)
            .onEach { _getLaporan.value = it }
            .launchIn(viewModelScope)
    }

}