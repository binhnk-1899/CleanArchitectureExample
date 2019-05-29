package com.binhnk.clean.architecture.data.remote.api

import com.binhnk.clean.architecture.data.remote.response.GetUserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/users")
    fun getUserByPage(@Query("page") page: String): Single<GetUserResponse>
}