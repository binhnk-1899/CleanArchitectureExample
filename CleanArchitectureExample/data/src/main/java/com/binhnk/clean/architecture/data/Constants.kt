package com.binhnk.clean.architecture.data

object Constants {
    const val DATABASE_VERSION = 3
    const val DATABASE_NAME = "RoomUserDB.db"
    const val MOCK_DATA = false

    const val BASE_URL = "https://reqres.in/"
    const val CONNECT_TIMEOUT = 10L
    const val READ_TIMEOUT = 10L
    const val WRITE_TIMEOUT = 10L

    const val MENU_SORT_NAME_AZ = 0
    const val MENU_SORT_NAME_ZA = 1
    const val MENU_SORT_ID = 2

    const val STATE_CREATING = 0
    const val STATE_CREATE_FAILED = 1
    const val STATE_CREATE_SUCCESS = 2
}