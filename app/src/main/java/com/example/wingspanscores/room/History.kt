package com.example.wingspanscores.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "histories")
data class History(
    @PrimaryKey val id: Long?,
)
