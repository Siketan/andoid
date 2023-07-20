package com.wahidabd.siketan.presentation.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.data.farm.model.store.response.ProductAddResponse
import com.wahidabd.siketan.domain.farm.FarmUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


class JournalViewModel(
    private val useCase: FarmUseCase
) : ViewModel(){

    private val _add = MutableLiveData<Resource<ProductAddResponse>>()
    val add: LiveData<Resource<ProductAddResponse>> get() = _add

    fun add(data: JournalAddRequest){
        useCase.addJournal(data)
            .onEach { _add.value = it }
            .launchIn(viewModelScope)
    }

}