package com.wahidabd.siketan

import androidx.appcompat.app.AppCompatDelegate
import com.wahidabd.library.presentation.BaseApplication
import com.wahidabd.siketan.di.apiModule
import com.wahidabd.siketan.di.appModule
import com.wahidabd.siketan.di.authModule
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
            apiModule,
            authModule
        )

    override fun initApp() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Timber.plant(Timber.DebugTree())
    }
}