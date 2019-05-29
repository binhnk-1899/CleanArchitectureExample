package com.binhnk.clean.architecture.domain.usecase.user

import com.binhnk.clean.architecture.domain.model.User
import com.binhnk.clean.architecture.domain.repository.UserRepository
import com.binhnk.clean.architecture.domain.usecase.UseCase
import io.reactivex.Single

open class GetUserUseCase(
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Params, Single<List<User>>>() {
    override fun createObservable(params: Params?): Single<List<User>> {
        params?.let {
            return userRepository.getUsersUsingRx(page = params.page)
        }
        return Single.error(Throwable("Page not found"))
    }

    override fun onCleared() {

    }

    data class Params(val page: Int? = 1)
}