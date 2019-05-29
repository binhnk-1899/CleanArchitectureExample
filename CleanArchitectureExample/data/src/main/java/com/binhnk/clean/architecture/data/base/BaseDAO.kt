package com.binhnk.clean.architecture.data.base

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

interface BaseDAO<T> {

    fun select(id: Int): Flowable<T>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(t: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(ts: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(t: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(ts:  List<T>)

    @Delete
    fun delete(t: T): Single<Int>

    @Delete
    fun delete(ts: List<T>)

    fun truncate()

}