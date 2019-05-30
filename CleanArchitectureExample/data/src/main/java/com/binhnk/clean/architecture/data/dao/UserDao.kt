package com.binhnk.clean.architecture.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.binhnk.clean.architecture.data.model.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserByUserId(userId: Int): Single<UserEntity?>

    @Query("SELECT * FROM users WHERE page = :page")
    fun getUsersByPage(page: Int): List<UserEntity>

    @Query("SELECT * FROM users WHERE first_name LIKE :key OR last_name LIKE :key OR email LIKE :key")
    fun getUserByName(key: String): List<UserEntity>

    @Query("SELECT * FROM users")
    fun getALlUser(): Single<List<UserEntity>>

    @Insert
    fun insertUserByRx(user: UserEntity): Long

    @Delete
    fun deleteUser(user: UserEntity): Int

    @Query("DELETE FROM users")
    fun deleteAllUser()

    @Update
    fun updateUser(vararg users: UserEntity)

    @Query("SELECT COUNT(id) FROM users")
    fun getDataCount(): LiveData<Int>

}