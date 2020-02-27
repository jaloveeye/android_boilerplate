package com.herace.android_boilerplate.data.local.temp

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Maybe

@Dao
interface TempDao {
    @Query("SELECT * FROM temp")
    fun getData(): LiveData<TempEntity>

    @Query("SELECT * FROM temp WHERE id = :id")
    fun getDataById(id: Int): Maybe<TempEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(temp: TempEntity)

    @Delete
    fun delete(temp: TempEntity)
}