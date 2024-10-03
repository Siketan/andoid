package id.go.ngawikab.siketan.di

import com.wahidabd.library.data.libs.OkHttpClientFactory
import id.go.ngawikab.siketan.BuildConfig
import id.go.ngawikab.siketan.utils.PrefManager
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


const val BASE_URL: String = "baseUrl"
const val ADDRESS_URL: String = "addressUrl"

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
    single(named(ADDRESS_URL)) { BuildConfig.BASE_URL }
}


private fun getHeaderInterceptor(pref: PrefManager): Interceptor {
    return Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        val token = pref.getToken()
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", token)
        }
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded")
        requestBuilder.addHeader("Content-Type", "multipart/form-data")
        chain.proceed(requestBuilder.build())
    }
}