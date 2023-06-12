package com.wahidabd.siketan.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.library.data.libs.OkHttpClientFactory
import com.wahidabd.library.data.libs.interceptor.HeaderInterceptor
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import com.wahidabd.siketan.BuildConfig
import com.wahidabd.siketan.utils.PrefManager
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


const val BASE_URL: String = "baseUrl"

val appModule = module {

    single { PrefManager(get()) }

    single {
        return@single OkHttpClientFactory.create(
            interceptors = listOf(getHeaderInterceptor(get())),
            showDebugLog = BuildConfig.DEBUG,
            authenticator = null,
            certificatePinner = null
        )
    }

    single(named(BASE_URL)) { BuildConfig.BASE_URL }
}

val apiModule = module {
    single {
        ApiService.createService(
            get(), get(named(BASE_URL))
        )
    }

    factory { ErrorParses(get()) }
}

private fun getHeaderInterceptor(pref: PrefManager): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"
    headers["Authorization"] = pref.getToken()

    return HeaderInterceptor(headers)
}