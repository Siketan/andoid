package com.wahidabd.siketan.presentation.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.domain.farm.FarmUseCase
import com.wahidabd.siketan.domain.farm.model.request.Chartparam
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 8/2/2023.
 * Github github.com/wahidabd.
 */


class ReportViewModel(private val useCase: FarmUseCase) : ViewModel() {

    private val _chart = MutableLiveData<Resource<ChartModel>>()
    val chart: LiveData<Resource<ChartModel>> get() = _chart

    fun getChart(data: Chartparam) {
        useCase.getChart(data)
            .onEach { _chart.value = it }
            .launchIn(viewModelScope)
    }

}