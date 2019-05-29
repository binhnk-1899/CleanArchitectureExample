package com.binhnk.clean.architecture.domain.usecase.user

import com.binhnk.clean.architecture.domain.model.User
import com.binhnk.clean.architecture.domain.repository.UserRepository
import com.binhnk.clean.architecture.domain.usecase.UseCase

class InsertToDBUseCase(
    private val userRepository: UserRepository
) : UseCase<InsertToDBUseCase.Params, Long>() {
    override fun createObservable(params: Params?): Long {
        params?.let {
            return userRepository.insertUserToDB(user = params.user)
        }
        return -1
    }

    override fun onCleared() {
        // do nothing
    }


    data class Params(val user: User)

}