package com.binhnk.clean.architecture.data.remote.response

import com.binhnk.clean.architecture.data.model.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetUserResponse(
        @SerializedName("page")
        @Expose var page: Int,
        @SerializedName("per_page")
        @Expose var perPage: Int,
        @SerializedName("total")
        @Expose var total: Int,
        @SerializedName("total_pages")
        @Expose var totalPages: Int,
        @SerializedName("data")
        @Expose var users: List<UserEntity>
)