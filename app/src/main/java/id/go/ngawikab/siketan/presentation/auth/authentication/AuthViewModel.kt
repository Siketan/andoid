package id.go.ngawikab.siketan.presentation.auth.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.library.data.Resource
import id.go.ngawikab.siketan.data.auth.model.LoginPenyuluhRequest
import id.go.ngawikab.siketan.domain.auth.AuthUseCase
import id.go.ngawikab.siketan.domain.auth.model.AuthResponse
import id.go.ngawikab.siketan.domain.auth.model.LoginRequest
import id.go.ngawikab.siketan.domain.auth.model.RegisterRequest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


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

    fun loginPenyuluh(data: LoginPenyuluhRequest){
        useCase.loginPenyuluh(data)
            .onEach { _login.value = it }
            .launchIn(viewModelScope)
    }

    fun register(data: RegisterRequest){
        useCase.register(data)
            .onEach { _register.value = it }
            .launchIn(viewModelScope)
    }

}