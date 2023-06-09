package com.wahidabd.siketan.di

import com.wahidabd.library.data.libs.OkHttpClientFactory
import com.wahidabd.library.data.libs.interceptor.HeaderInterceptor
import com.wahidabd.siketan.BuildConfig
import com.wahidabd.siketan.utils.PrefManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


const val BASE_URL: String = "baseUrl"

val apiModule = module {

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

val rxModule = module {
    factory { CompositeDisposable() }
}

private fun getHeaderInterceptor(pref: PrefManager): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"
    headers["Authorization"] = pref.getToken()

    return HeaderInterceptor(headers)
}