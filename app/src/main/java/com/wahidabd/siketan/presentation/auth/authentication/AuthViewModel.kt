package com.wahidabd.siketan.presentation.auth.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.observerScheduler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.siketan.domain.auth.AuthUseCase
import com.wahidabd.siketan.domain.auth.model.AuthResponse
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthViewModel(
    private val useCase: AuthUseCase,
) : ViewModel(){

    private val _login = MutableLiveData<Resource<AuthResponse>>()
    val login: LiveData<Resource<AuthResponse>> get() = _login

    private val _register = MutableLiveData<Resource<AuthResponse>>()
    val register: LiveData<Resource<AuthResponse>> get() = _register

    init {
        _login.value = Resource.default()
        _register.value = Resource.default()
    }

    fun login(data: LoginRequest){
        useCase.login(data)
            .onEach { _login.value = it }
            .launchIn(viewModelScope)
    }

    fun register(data: RegisterRequest){
        useCase.register(data)
            .onEach { _register.value = it }
            .launchIn(viewModelScope)
    }

}