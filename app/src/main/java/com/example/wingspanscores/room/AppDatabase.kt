package com.example.wingspanscores.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Player::class, Score::class, History::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "wingspan2.db"

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        )
            .build()
    }
}