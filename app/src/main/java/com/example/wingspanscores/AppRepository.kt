package com.example.wingspanscores

import androidx.annotation.WorkerThread
import com.example.wingspanscores.room.*
import kotlinx.coroutines.flow.Flow

class AppRepository(private val dao: AppDao) {
    val players: Flow<List<Player>> = dao.getPlayers()

    suspend fun getPlayerByName(name: String): Player? {
        return dao.getPlayerByName(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertPlayer(player: Player): Long {
        return dao.insertPlayer(player)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertScore(score: Score): Long {
        return dao.insertScore(score)
    }

    val playersForList: Flow<List<PlayersForList>> = dao.getPlayersForList()

    fun getScoresByPlayer(name: String): Flow<List<ScoreByPlayer>> {
        return dao.getScoresByPlayer(name)
    }
}