package com.wahidabd.siketan.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.siketan.presentation.chat.ChatViewModel
import com.wahidabd.siketan.data.chat.ChatDataSource
import go.ngawikab.siketan.data.chat.ChatRepository
import com.wahidabd.siketan.data.chat.remote.ChatApi
import com.wahidabd.siketan.data.chat.remote.ChatApiClient
import com.wahidabd.siketan.domain.chat.ChatInteractor
import com.wahidabd.siketan.domain.chat.ChatUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 8/6/2023.
 * Github github.com/wahidabd.
 */


val chatModule = module {
    single {
        ApiService.createService(
            ChatApiClient::class.java,
            get(), get(named(BASE_URL))
        )
    }

    factory { ErrorParses(get()) }
    single { ChatApi(get()) }
    single<ChatRepository> { ChatDataSource(get(), get()) }
    single<ChatUseCase> { ChatInteractor(get()) }
    viewModel { ChatViewModel(get()) }
}