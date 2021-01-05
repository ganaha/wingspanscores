package com.example.wingspanscores.room

import androidx.room.Embedded
import androidx.room.Relation

data class HistoryWithScores(
    @Embedded val history: History,
    @Relation(
        parentColumn = "id",
        entityColumn = "history_id",
        entity = Score::class
    ) var scores: List<ScoreAndPlayer>
)
