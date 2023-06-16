package com.wahidabd.siketan.presentation.announcement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.domain.farm.FarmUseCase
import com.wahidabd.siketan.domain.farm.model.InfoTani
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

    fun infoTani() {
        useCase.getInfoTani()
            .onEach { _infoTani.value = it }
            .launchIn(viewModelScope)
    }

}