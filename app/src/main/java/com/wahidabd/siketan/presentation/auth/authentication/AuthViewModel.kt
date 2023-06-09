package com.wahidabd.siketan.presentation.auth.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahidabd.library.data.Resource
import com.wahidabd.library.presentation.BaseViewModel
import com.wahidabd.library.utils.exts.addTo
import com.wahidabd.library.utils.rx.apihandlers.genericErrorHandler
import com.wahidabd.library.utils.rx.transformers.singleScheduler
import com.wahidabd.siketan.domain.auth.AuthUseCase
import com.wahidabd.siketan.domain.auth.model.AuthResponse
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import io.reactivex.rxjava3.disposables.CompositeDisposable


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class AuthViewModel(
    private val useCase: AuthUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val _login = MutableLiveData<Resource<AuthResponse>>()
    val login: LiveData<Resource<AuthResponse>> get() = _login

    private val _register = MutableLiveData<Resource<AuthResponse>>()
    val register: LiveData<Resource<AuthResponse>> get() = _register

    init {
        _login.value = Resource.default()
        _register.value = Resource.default()
    }

    fun login(data: LoginRequest){
        _login.value = Resource.loading()

        useCase.login(data)
            .compose(singleScheduler())
            .subscribe({
                _login.value = Resource.success(it)
            }, { genericErrorHandler(it, _login) })
            .addTo(disposable)
    }

}