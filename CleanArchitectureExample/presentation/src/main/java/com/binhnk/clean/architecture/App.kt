package com.binhnk.clean.architecture

import android.app.Application
import com.binhnk.clean.architecture.data.di.networkModule
import com.binhnk.clean.architecture.data.di.repositoryModules
import com.binhnk.clean.architecture.di.appModule
import com.binhnk.clean.architecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, networkModule, repositoryModules, viewModelModule)
        }
    }
}