package com.binhnk.clean.architecture.data.local.db

import androidx.room.Database
import com.binhnk.clean.architecture.data.Constants
import com.binhnk.clean.architecture.data.dao.UserDao
import com.binhnk.clean.architecture.data.model.UserEntity

@Database(entities = [UserEntity::class], version = Constants.DATABASE_VERSION, exportSchema = false)
abstract class UserDatabase : AppDatabase() {

    abstract fun userDAO(): UserDao

}