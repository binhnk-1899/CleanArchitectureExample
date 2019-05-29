package com.binhnk.clean.architecture.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binhnk.clean.architecture.data.Constants
import com.binhnk.clean.architecture.data.dao.UserDAO
import com.binhnk.clean.architecture.data.model.UserEntity
import com.binhnk.clean.architecture.domain.model.User

@Database(entities = [UserEntity::class], version = Constants.DATABASE_VERSION, exportSchema = false)
abstract class UserDatabase : AppDatabase() {

    abstract fun userDAO(): UserDAO

}