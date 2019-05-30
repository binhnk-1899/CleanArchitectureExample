package com.binhnk.clean.architecture.domain.usecase.user

import com.binhnk.clean.architecture.domain.model.User
import com.binhnk.clean.architecture.domain.repository.UserRepository
import com.binhnk.clean.architecture.domain.usecase.UseCase
import io.reactivex.Single

class QueryAllUserUseCase(
    private val userRepository: UserRepository
) : UseCase<QueryAllUserUseCase.Params, Single<List<User>>>() {

    override fun createObservable(params: Params?): Single<List<User>> {
        run {
            return userRepository.getAllUser()
        }
    }

    override fun onCleared() {

    }

    class Params
}