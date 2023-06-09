package com.wahidabd.siketan.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.siketan.data.auth.AuthDataSource
import com.wahidabd.siketan.data.auth.AuthRepository
import com.wahidabd.siketan.data.auth.remote.AuthApi
import com.wahidabd.siketan.data.auth.remote.AuthApiClient
import com.wahidabd.siketan.domain.auth.AuthInteractor
import com.wahidabd.siketan.domain.auth.AuthUseCase
import com.wahidabd.siketan.presentation.auth.authentication.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


val authModule = module {

    single {
        ApiService.createReactiveService(
            AuthApiClient::class.java,
            get(), get(named(BASE_URL))
        )
    }

    single { AuthApi(get()) }
    single<AuthRepository> { AuthDataSource(get()) }
    single<AuthUseCase> { AuthInteractor(get()) }
    viewModel { AuthViewModel(get(), get()) }
}