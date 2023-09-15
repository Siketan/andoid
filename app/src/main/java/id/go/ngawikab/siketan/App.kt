package id.go.ngawikab.siketan

import androidx.appcompat.app.AppCompatDelegate
import com.wahidabd.library.presentation.BaseApplication
import id.go.ngawikab.siketan.di.addressModule
import id.go.ngawikab.siketan.di.appModule
import id.go.ngawikab.siketan.di.authModule
import id.go.ngawikab.siketan.di.chatModule
import id.go.ngawikab.siketan.di.farmModule
import org.koin.core.module.Module
import timber.log.Timber


/**
 * Created by Wahid on 6/8/2023.
 * Github github.com/wahidabd.
 */


class App : BaseApplication() {

    override fun getDefineModule(): List<Module> =
        listOf(
            appModule,
            authModule,
            farmModule,
            addressModule,
            chatModule
        )

    override fun initApp() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Timber.plant(Timber.DebugTree())
    }
}