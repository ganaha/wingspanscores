package com.example.wingspanscores.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "players", indices = [Index(value = ["name"], unique = true)])
data class Player(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "name") val name: String
)
