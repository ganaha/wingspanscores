package com.example.wingspanscores.room

data class ScoreByPlayer(
    val name: String,
    val birds: Int,
    val bonus: Int,
    val round: Int,
    val eggs: Int,
    val food: Int,
    val tucked: Int,
    val total: Int,
    val rank: Int
)
