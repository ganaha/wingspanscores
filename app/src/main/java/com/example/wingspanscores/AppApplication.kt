package com.example.wingspanscores

import android.app.Application
import com.example.wingspanscores.room.AppDatabase

class AppApplication : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { AppRepository(database.appDao()) }
}