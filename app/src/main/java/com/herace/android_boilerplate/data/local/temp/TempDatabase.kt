package com.herace.android_boilerplate.data.local.temp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TempEntity::class], version = 1, exportSchema = false)
abstract class TempDatabase: RoomDatabase() {

    abstract fun getDao(): TempDao

    companion object {
        private var INSTANCE: TempDatabase? = null

        fun getInstance(context: Context): TempDatabase? {
            if (INSTANCE == null) {
                synchronized(TempDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TempDatabase::class.java,
                        "temp")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}