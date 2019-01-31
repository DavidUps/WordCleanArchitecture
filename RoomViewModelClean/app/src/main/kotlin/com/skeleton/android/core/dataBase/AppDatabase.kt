package com.skeleton.android.core.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.skeleton.android.core.dao.WordDAO
import com.skeleton.android.features.word.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun wordEntityDao(): WordDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java,
                            "wordDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
