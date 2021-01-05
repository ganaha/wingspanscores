package com.example.wingspanscores.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "player_id") val playerId: Long,
    @ColumnInfo val birds: Int,
    @ColumnInfo val bonus: Int,
    @ColumnInfo val round: Int,
    @ColumnInfo val eggs: Int,
    @ColumnInfo val food: Int,
    @ColumnInfo val tucked: Int,
    @ColumnInfo val total: Int,
    @ColumnInfo val rank: Int,
    @ColumnInfo(name = "history_id") val historyId: Long?
)
