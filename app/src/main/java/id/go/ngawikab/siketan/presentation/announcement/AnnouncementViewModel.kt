package id.go.ngawikab.siketan.presentation.announcement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.domain.farm.FarmUseCase
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


class AnnouncementViewModel(
    private val useCase: FarmUseCase
) : ViewModel() {

    private val _infoTani = MutableLiveData<Resource<List<InfoTani>>>()
    val infoTani: LiveData<Resource<List<InfoTani>>> get() = _infoTani

    private val _event = MutableLiveData<Resource<List<EventTani>>>()
    val event: LiveData<Resource<List<EventTani>>> get() = _event

    var selectedRadioButtonId: Int = R.id.rbNews

    var currentPage = 0
    val pageSize = 5
    private val allInfoTani = mutableListOf<InfoTani>()
    private val allEventTani = mutableListOf<EventTani>()

    fun getInfoTaniSize() = allInfoTani.size
    fun getEventTaniSize() = allEventTani.size

    fun fetchData() {
        if (selectedRadioButtonId == R.id.rbNews) {
            fetchInfoTani()
        } else {
            fetchEventTani()
        }
    }

    private fun fetchInfoTani() {
        useCase.getInfoTani()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        allInfoTani.clear()
                        allInfoTani.addAll(resource.data)
                        updateInfoTani()
                    }
                    else -> _infoTani.value = resource
                }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchEventTani() {
        useCase.getEvent()
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        allEventTani.clear()
                        allEventTani.addAll(resource.data)
                        updateEventTani()
                    }
                    else -> _event.value = resource
                }
            }
            .launchIn(viewModelScope)
    }

    fun updateInfoTani() {
        val startIndex = currentPage * pageSize
        val endIndex = minOf(startIndex + pageSize, allInfoTani.size)
        _infoTani.value = Resource.Success(allInfoTani.subList(startIndex, endIndex))
    }

    fun updateEventTani() {
        val startIndex = currentPage * pageSize
        val endIndex = minOf(startIndex + pageSize, allEventTani.size)
        _event.value = Resource.Success(allEventTani.subList(startIndex, endIndex))
    }

    fun loadNextPage() {
        val totalItems = if (selectedRadioButtonId == R.id.rbNews) allInfoTani.size else allEventTani.size
        val totalPages = (totalItems + pageSize - 1) / pageSize
        if (currentPage < totalPages - 1) {
            currentPage++
            if (selectedRadioButtonId == R.id.rbNews) {
                updateInfoTani()
            } else {
                updateEventTani()
            }
        }
    }

    fun loadPreviousPage() {
        if (currentPage > 0) {
            currentPage--
            if (selectedRadioButtonId == R.id.rbNews) {
                updateInfoTani()
            } else {
                updateEventTani()
            }
        }
    }
}