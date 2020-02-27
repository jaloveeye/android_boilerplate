package com.herace.android_boilerplate.data.local.fragmentA

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface FragmentADao {
    @Query("SELECT * FROM fragmentA WHERE id = :id")
    fun getDataById(id: Int) : Maybe<List<FragmentAEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fragmentAEntityList: List<FragmentAEntity>)

    @Delete
    fun delete(fragmentAEntity: FragmentAEntity)
}