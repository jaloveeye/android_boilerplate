package com.herace.android_boilerplate.data.local.fragmentA

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FragmentAEntity::class], version = 1 , exportSchema = false)
abstract class FragmentADatabase : RoomDatabase() {
    abstract fun getDao() : FragmentADao

    companion object {
        private var INSTANCE : FragmentADatabase? = null

        fun getInstance(context: Context) : FragmentADatabase? {
            if(FragmentADatabase.INSTANCE == null) {
                synchronized(FragmentADatabase::class) {
                    FragmentADatabase.INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FragmentADatabase::class.java, "fragmentA")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return FragmentADatabase.INSTANCE
        }
    }

}