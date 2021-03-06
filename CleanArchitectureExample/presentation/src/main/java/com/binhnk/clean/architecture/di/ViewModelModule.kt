package com.binhnk.clean.architecture.di

import com.binhnk.clean.architecture.domain.usecase.user.GetUserUseCase
import com.binhnk.clean.architecture.domain.usecase.user.InsertToDBUseCase
import com.binhnk.clean.architecture.domain.usecase.user.QueryAllUserUseCase
import com.binhnk.clean.architecture.domain.usecase.user.QueryUserByIDUseCase
import com.binhnk.clean.architecture.model.UserItemMapper
import com.binhnk.clean.architecture.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            GetUserUseCase(get()),
            InsertToDBUseCase(get()),
            QueryUserByIDUseCase(get()),
            QueryAllUserUseCase(get()),
            get(),
            UserItemMapper()
        )
    }
}