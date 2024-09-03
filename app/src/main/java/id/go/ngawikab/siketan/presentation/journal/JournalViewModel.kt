package id.go.ngawikab.siketan.presentation.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.farm.model.journal.JournalAddRequest
import id.go.ngawikab.siketan.data.farm.model.journal.JournalResponse
import id.go.ngawikab.siketan.data.farm.model.journal.PresensiRequest
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.FarmUseCase
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


class JournalViewModel(
    private val useCase: FarmUseCase
) : ViewModel() {

    private val _add = MutableLiveData<Resource<GenericAddResponse>>()
    val add: LiveData<Resource<GenericAddResponse>> get() = _add

    private val _get = MutableLiveData<Resource<JournalResponse>>()
    val get: LiveData<Resource<JournalResponse>> get() = _get

    private val _presensi = MutableLiveData<Resource<GenericAddResponse>>()
    val presensi: LiveData<Resource<GenericAddResponse>> get() = _presensi

    fun add(data: JournalAddRequest) {
        useCase.addJournal(data)
            .onEach { _add.value = it }
            .launchIn(viewModelScope)
    }

    fun get() {
        useCase.getJournal()
            .onEach { _get.value = it }
            .launchIn(viewModelScope)
    }

    fun presensi(data: PresensiRequest) {
        useCase.addPresensi(data)
            .onEach { _presensi.value = it }
            .launchIn(viewModelScope)
    }

}