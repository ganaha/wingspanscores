package com.example.wingspanscores.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "name") val name: String
)
