package com.binhnk.clean.architecture.domain.usecase.user

import com.binhnk.clean.architecture.domain.model.User
import com.binhnk.clean.architecture.domain.usecase.UseCase

class CheckStorageStatusUseCase : UseCase<CheckStorageStatusUseCase.Params, Boolean>() {
    
    override fun createObservable(params: Params?): Boolean {

    }

    override fun onCleared() {

    }

    data class Params(val user: User)
}