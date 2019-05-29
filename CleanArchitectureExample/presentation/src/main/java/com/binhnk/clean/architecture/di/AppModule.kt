package com.binhnk.clean.architecture.di

import com.binhnk.clean.architecture.rx.AppSchedulerProvider
import com.binhnk.clean.architecture.rx.SchedulerProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { androidApplication().resources }
    single<SchedulerProvider> { AppSchedulerProvider() }
}