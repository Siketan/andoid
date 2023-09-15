package id.go.ngawikab.siketan.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.library.utils.coroutine.handler.ErrorParses
import id.go.ngawikab.siketan.data.farm.FarmDataSource
import id.go.ngawikab.siketan.data.farm.FarmRepository
import id.go.ngawikab.siketan.data.farm.remote.FarmApi
import id.go.ngawikab.siketan.data.farm.remote.FarmApiClient
import id.go.ngawikab.siketan.domain.farm.FarmInteractor
import id.go.ngawikab.siketan.domain.farm.FarmUseCase
import id.go.ngawikab.siketan.presentation.announcement.AnnouncementViewModel
import id.go.ngawikab.siketan.presentation.journal.JournalViewModel
import id.go.ngawikab.siketan.presentation.report.viewmodel.ReportViewModel
import id.go.ngawikab.siketan.presentation.store.viewmodel.StoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


val farmModule = module {

    single {
        ApiService.createService(
            FarmApiClient::class.java,
            get(), get(named(BASE_URL))
        )
    }

    factory { ErrorParses(get()) }

//    single { get<Retrofit>().create(FarmApiClient::class.java) }
    single { FarmApi(get()) }
    single<FarmRepository> { FarmDataSource(get(), get()) }
    single<FarmUseCase> { FarmInteractor(get()) }
    viewModel { AnnouncementViewModel(get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { JournalViewModel(get()) }
    viewModel { ReportViewModel(get()) }

}