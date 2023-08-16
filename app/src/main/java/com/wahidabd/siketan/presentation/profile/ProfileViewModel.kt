package com.wahidabd.siketan.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.siketan.data.auth.model.user.DetailPetaniResponse
import com.wahidabd.siketan.data.auth.model.user.UserEditeRequest
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.auth.AuthUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * Created by Wahid on 8/8/2023.
 * Github github.com/wahidabd.
 */


class ProfileViewModel(private val useCase: AuthUseCase) : ViewModel() {

    private val _user = MutableLiveData<Resource<DetailPetaniResponse>>()
    val user: LiveData<Resource<DetailPetaniResponse>> get() = _user

    private val _edit = MutableLiveData<Resource<GenericAddResponse>>()
    val edit: LiveData<Resource<GenericAddResponse>> get() = _edit

    fun user(id: Int) {
        useCase.getUser(id)
            .onEach { _user.value = it }
            .launchIn(viewModelScope)
    }

    fun edit(data: UserEditeRequest){
        useCase.editUser(data)
            .onEach { _edit.value = it }
            .launchIn(viewModelScope)
    }

}