package com.wahidabd.siketan.di

import com.wahidabd.siketan.data.farm.FarmDataSource
import com.wahidabd.siketan.data.farm.FarmRepository
import com.wahidabd.siketan.data.farm.remote.FarmApi
import com.wahidabd.siketan.data.farm.remote.FarmApiClient
import com.wahidabd.siketan.domain.farm.FarmInteractor
import com.wahidabd.siketan.domain.farm.FarmUseCase
import com.wahidabd.siketan.presentation.announcement.AnnouncementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


val farmModule = module {

    single { get<Retrofit>().create(FarmApiClient::class.java) }
    single { FarmApi(get()) }
    single<FarmRepository> { FarmDataSource(get(), get()) }
    single<FarmUseCase> { FarmInteractor(get()) }
    viewModel { AnnouncementViewModel(get()) }

}