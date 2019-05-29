package com.binhnk.clean.architecture.domain.usecase.user

import com.binhnk.clean.architecture.domain.model.User
import com.binhnk.clean.architecture.domain.repository.UserRepository
import com.binhnk.clean.architecture.domain.usecase.UseCase
import io.reactivex.Single

class QueryUserUseCase(
    private val userRepository: UserRepository
) : UseCase<QueryUserUseCase.Params, Single<User?>>() {

    override fun createObservable(params: Params?): Single<User?> {
        params?.let { return userRepository.getUserByUserId(userId = params.userId) }
        return Single.just(null)
    }

    override fun onCleared() {

    }

    data class Params(val userId: Int)
}