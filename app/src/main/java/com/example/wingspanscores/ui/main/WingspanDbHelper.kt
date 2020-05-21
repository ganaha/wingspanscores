package com.example.wingspanscores.ui.main

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WingspanDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "wingspan.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL("""
            CREATE TABLE IF NOT EXISTS players (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT UNIQUE,
                created_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime')),
                updated_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))
            )
        """)

        database?.execSQL("""
            CREATE TABLE IF NOT EXISTS scores (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                player_id INTEGER,
                birds INTEGER,
                bonus INTEGER,
                round INTEGER,
                eggs INTEGER,
                food INTEGER,
                tucked INTEGER,
                total INTEGER,
                rank INTEGER,
                created_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime')),
                updated_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime')),
                FOREIGN KEY(player_id) REFERENCES players(id)
            )
        """)

        database?.execSQL("""
            INSERT INTO players (name) VALUES ('Automa')            
        """)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}