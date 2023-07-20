package com.wahidabd.siketan.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.siketan.data.address.AddressDataSource
import com.wahidabd.siketan.data.address.AddressRepository
import com.wahidabd.siketan.data.address.remote.AddressApi
import com.wahidabd.siketan.data.address.remote.AddressApiClient
import com.wahidabd.siketan.domain.address.AddressInteractor
import com.wahidabd.siketan.domain.address.AddressUseCase
import com.wahidabd.siketan.presentation.journal.JournalViewModel
import com.wahidabd.siketan.presentation.plant.AddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


val addressModule = module {
    single {
        ApiService.createService(
            get(), get(named(ADDRESS_URL))
        )
    }

    factory { ErrorParses(get()) }

    single { get<Retrofit>().create(AddressApiClient::class.java) }
    single { AddressApi(get()) }
    single<AddressRepository> { AddressDataSource(get(), get()) }
    single<AddressUseCase> { AddressInteractor(get()) }
    viewModel { AddressViewModel(get()) }
    viewModel { JournalViewModel(get()) }
}
