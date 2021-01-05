package com.example.wingspanscores.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Player::class, Score::class, History::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "wingspan2.db"

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            buildDatabase(context).also { INSTANCE = it }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // playersテーブル作成
                database.execSQL(
                    """
                    CREATE TABLE players (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL 
                       )
                    """.trimIndent()
                )
                // scoresテーブル作成
                database.execSQL(
                    """
                    CREATE TABLE scores (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        player_id INTEGER NOT NULL,
                        birds INTEGER NOT NULL,
                        bonus INTEGER NOT NULL,
                        round INTEGER NOT NULL,
                        eggs INTEGER NOT NULL,
                        food INTEGER NOT NULL,
                        tucked INTEGER NOT NULL,
                        total INTEGER NOT NULL,
                        rank INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // historiesテーブル作成
                database.execSQL(
                    """
                    CREATE TABLE `histories` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT
                    )
                    """.trimIndent()
                )
                // storesテーブルにhistory_id追加
                database.execSQL(
                    """
                    ALTER TABLE scores ADD COLUMN history_id INTEGER
                    """.trimIndent()
                )
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        )
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }
}