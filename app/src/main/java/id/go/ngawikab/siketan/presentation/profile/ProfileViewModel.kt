package id.go.ngawikab.siketan.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.auth.model.user.DetailPenyuluhResponse
import id.go.ngawikab.siketan.data.auth.model.user.DetailPetaniResponse
import id.go.ngawikab.siketan.data.auth.model.user.UserEditeRequest
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.auth.AuthUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 8/8/2023.
 * Github github.com/wahidabd.
 */


class ProfileViewModel(private val useCase: AuthUseCase) : ViewModel() {

    private val _userPenyuluh = MutableLiveData<Resource<DetailPenyuluhResponse>>()

    val userPenyuluh: LiveData<Resource<DetailPenyuluhResponse>> get() = _userPenyuluh

    private val _user = MutableLiveData<Resource<DetailPetaniResponse>>()
    val user: LiveData<Resource<DetailPetaniResponse>> get() = _user

    private val _edit = MutableLiveData<Resource<GenericAddResponse>>()
    val edit: LiveData<Resource<GenericAddResponse>> get() = _edit

    fun user(id: Int) {
        useCase.getUser(id)
            .onEach { _user.value = it }
            .launchIn(viewModelScope)
    }
    fun userPenyuluh(id: Int) {
        useCase.getUserPenyuluh(id)
            .onEach { _userPenyuluh.value = it }
            .launchIn(viewModelScope)
    }

    fun edit(data: UserEditeRequest){
        useCase.editUser(data)
            .onEach { _edit.value = it }
            .launchIn(viewModelScope)
    }

}