package id.go.ngawikab.siketan.presentation.report.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.request.LaporanTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.ChartResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InputTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.FarmUseCase
import id.go.ngawikab.siketan.domain.farm.model.request.Chartparam
import id.go.ngawikab.siketan.domain.farm.model.response.OpsiPetani
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 8/2/2023.
 * Github github.com/wahidabd.
 */


class ReportViewModel(private val useCase: FarmUseCase) : ViewModel() {

    private val _chart = MutableLiveData<Resource<ChartResponse>>()
    val chart: LiveData<Resource<ChartResponse>> get() = _chart

    private val _addTanaman = MutableLiveData<Resource<InputTanamanResponse>>()
    val addTanaman: LiveData<Resource<InputTanamanResponse>> get() = _addTanaman

    private val _addLaporan = MutableLiveData<Resource<GenericAddResponse>>()
    val addLaporan: LiveData<Resource<GenericAddResponse>> get() = _addLaporan

    private val _getLaporan = MutableLiveData<Resource<ReportTanamanResponse>>()
    val getLaporan: LiveData<Resource<ReportTanamanResponse>> get() = _getLaporan

    private val _petani = MutableLiveData<Resource<List<OpsiPetani>>>()
    val petani: LiveData<Resource<List<OpsiPetani>>> get() = _petani

    val selectedFarmerId : LiveData<OpsiPetani> get() = _selectedFarmerId
    private val _selectedFarmerId = MutableLiveData<OpsiPetani>()


    fun selectFarmer(data: OpsiPetani){
        viewModelScope.launch {
            _selectedFarmerId.value = data
        }
    }

    fun getChart(data: Chartparam) {
        useCase.getChart(data)
            .onEach { _chart.value = it }
            .launchIn(viewModelScope)
    }

    fun tanaman(id:Int) : Flow<PagingData<PlantFarmerData>> {
      return useCase.getTanaman(id).distinctUntilChanged()
    }

    fun getPetani(id:Int) {
        viewModelScope.launch {
            useCase.getPetani(id)
                .onEach { _petani.value = it}
                .launchIn(viewModelScope)
        }
    }

    fun addTanaman(data: InputTanamanRequest){
        useCase.addTanaman(data)
            .onEach { _addTanaman.value = it }
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