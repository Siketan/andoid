package id.go.ngawikab.siketan.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import id.go.ngawikab.siketan.data.address.AddressDataSource
import id.go.ngawikab.siketan.data.address.AddressRepository
import id.go.ngawikab.siketan.data.address.remote.AddressApi
import id.go.ngawikab.siketan.data.address.remote.AddressApiClient
import id.go.ngawikab.siketan.domain.address.AddressInteractor
import id.go.ngawikab.siketan.domain.address.AddressUseCase
import id.go.ngawikab.siketan.presentation.report.viewmodel.AddressViewModel
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
}
