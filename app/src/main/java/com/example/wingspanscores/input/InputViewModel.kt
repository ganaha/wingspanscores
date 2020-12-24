package com.example.wingspanscores.input

import androidx.lifecycle.*
import com.example.wingspanscores.AppRepository
import com.example.wingspanscores.room.Player
import com.example.wingspanscores.room.Score
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class InputViewModel(private val repository: AppRepository) : ViewModel() {
    val players: LiveData<List<Player>> = repository.players.asLiveData()

    /**
     * nameからplayer_idを取得する。なければ新規登録。
     */
    fun getPlayerId(name: String): Long? {
        if (name.isBlank()) return null

        var playerId = -1L
        runBlocking {
            val player = repository.getPlayerByName(name)
            playerId = player?.id ?: repository.insertPlayer(Player(null, name))
        }
        return playerId
    }

    /**
     * Score登録
     */
    fun insertScore(
        playerId: Long?,
        birds: Int,
        bonus: Int,
        round: Int,
        eggs: Int,
        food: Int,
        tucked: Int,
        total: Int,
        rank: Int
    ) {
        if (playerId == null) return

        viewModelScope.launch {
            val score = Score(null, playerId, birds, bonus, round, eggs, food, tucked, total, rank)
            repository.insertScore(score)
        }
    }
}

class InputViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InputViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InputViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}