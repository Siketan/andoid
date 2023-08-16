package com.wahidabd.siketan.di

import com.wahidabd.siketan.presentation.chat.ChatSocketListener
import com.wahidabd.siketan.presentation.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 8/6/2023.
 * Github github.com/wahidabd.
 */


val chatModule = module {
    viewModel { ChatViewModel() }
//    single { ChatSocketListener(get()) }
}