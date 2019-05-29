package com.binhnk.clean.architecture.domain.model

data class User(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    var page: Int,
    var addedInDB: Boolean = false
) : Model()
