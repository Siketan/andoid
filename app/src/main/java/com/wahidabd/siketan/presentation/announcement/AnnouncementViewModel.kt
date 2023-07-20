package com.wahidabd.siketan.presentation.announcement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.domain.farm.FarmUseCase
import com.wahidabd.siketan.domain.farm.model.response.EventTani
import com.wahidabd.siketan.domain.farm.model.response.InfoTani
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty


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

    fun infoTani() {
        useCase.getInfoTani()
            .onEach { _infoTani.value = it }
            .launchIn(viewModelScope)
    }

    fun event() {
        useCase.getEvent()
            .onEach { _event.value = it }
            .onEmpty { _event.value = Resource.empty() }
            .launchIn(viewModelScope)
    }

}