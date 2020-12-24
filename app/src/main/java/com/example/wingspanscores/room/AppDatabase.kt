package com.example.wingspanscores.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class, Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val ASSET_DB_NAME = "database/wingspan2.db"
        private const val DB_NAME = "wingspan2.db"

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        )
            .createFromAsset(ASSET_DB_NAME)
            .fallbackToDestructiveMigrationFrom(1)
            .build()
    }
}