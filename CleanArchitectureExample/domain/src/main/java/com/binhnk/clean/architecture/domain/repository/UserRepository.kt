package com.binhnk.clean.architecture.domain.repository

import com.binhnk.clean.architecture.domain.model.User
import io.reactivex.Single

interface UserRepository : Repository {

    /**
     * get user from server
     */
    fun getUsersUsingRx(page: Int? = 0): Single<List<User>>

    /**
     * insert user to database
     */
    fun insertUserToDB(user: User): Long

    /**
     * get user by user id
     */
    fun getUserByUserId(userId: Int): Single<User?>

    /**
     * get all user
     */
    fun getAllUser(): Single<List<User>>
}