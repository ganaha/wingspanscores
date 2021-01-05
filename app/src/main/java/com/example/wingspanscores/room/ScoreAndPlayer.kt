package com.example.wingspanscores.room

import androidx.room.Embedded
import androidx.room.Relation

data class ScoreAndPlayer(
    @Embedded val score: Score,
    @Relation(
        parentColumn = "player_id",
        entityColumn = "id",
        entity = Player::class
    ) val player: Player
)
