package com.example.wingspanscores.input

import androidx.lifecycle.*
import com.example.wingspanscores.AppRepository
import com.example.wingspanscores.room.History
import com.example.wingspanscores.room.Player
import com.example.wingspanscores.room.Score
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class InputViewModel(private val repository: AppRepository) : ViewModel() {
    val players: LiveData<List<Player>> = repository.players.asLiveData()

    /**
     * nameからplayer_idを取得する。なければ新規登録。
     */
    private fun getPlayerId(name: String): Long? {
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
    private fun insertScore(
        playerId: Long?,
        birds: Int,
        bonus: Int,
        round: Int,
        eggs: Int,
        food: Int,
        tucked: Int,
        total: Int,
        rank: Int,
        historyId: Long
    ) {
        if (playerId == null) return

        viewModelScope.launch {
            val score =
                Score(
                    null,
                    playerId,
                    birds,
                    bonus,
                    round,
                    eggs,
                    food,
                    tucked,
                    total,
                    rank,
                    historyId
                )
            repository.insertScore(score)
        }
    }

    /**
     * Score一括登録
     */
    fun insertScores() {
        if (p1Score.name.value?.isEmpty() == true) return

        val historyId = getHistoryId()

        // P1
        val p1Name = p1Score.name.value ?: ""
        val p1PlayerId = getPlayerId(p1Name)
        val p1Birds = p1Score.birds.value?.toIntOrNull() ?: 0
        val p1Bonus = p1Score.bonus.value?.toIntOrNull() ?: 0
        val p1Round = p1Score.round.value?.toIntOrNull() ?: 0
        val p1Eggs = p1Score.eggs.value?.toIntOrNull() ?: 0
        val p1Food = p1Score.food.value?.toIntOrNull() ?: 0
        val p1Tucked = p1Score.tucked.value?.toIntOrNull() ?: 0
        val p1Total = p1Score.total.value?.toIntOrNull() ?: 0

        // P2
        val p2Name = p2Score.name.value ?: ""
        val p2PlayerId = getPlayerId(p2Name)
        val p2Birds = p2Score.birds.value?.toIntOrNull() ?: 0
        val p2Bonus = p2Score.birds.value?.toIntOrNull() ?: 0
        val p2Round = p2Score.round.value?.toIntOrNull() ?: 0
        val p2Eggs = p2Score.eggs.value?.toIntOrNull() ?: 0
        val p2Food = p2Score.food.value?.toIntOrNull() ?: 0
        val p2Tucked = p2Score.tucked.value?.toIntOrNull() ?: 0
        val p2Total = p2Score.total.value?.toIntOrNull() ?: 0

        // P3
        val p3Name = p3Score.name.value ?: ""
        val p3PlayerId = getPlayerId(p3Name)
        val p3Birds = p3Score.birds.value?.toIntOrNull() ?: 0
        val p3Bonus = p3Score.bonus.value?.toIntOrNull() ?: 0
        val p3Round = p3Score.round.value?.toIntOrNull() ?: 0
        val p3Eggs = p3Score.eggs.value?.toIntOrNull() ?: 0
        val p3Food = p3Score.food.value?.toIntOrNull() ?: 0
        val p3Tucked = p3Score.tucked.value?.toIntOrNull() ?: 0
        val p3Total = p3Score.total.value?.toIntOrNull() ?: 0

        // P4
        val p4Name = p4Score.name.value ?: ""
        val p4PlayerId = getPlayerId(p4Name)
        val p4Birds = p4Score.birds.value?.toIntOrNull() ?: 0
        val p4Bonus = p4Score.bonus.value?.toIntOrNull() ?: 0
        val p4Round = p4Score.round.value?.toIntOrNull() ?: 0
        val p4Eggs = p4Score.eggs.value?.toIntOrNull() ?: 0
        val p4Food = p4Score.food.value?.toIntOrNull() ?: 0
        val p4Tucked = p4Score.tucked.value?.toIntOrNull() ?: 0
        val p4Total = p4Score.total.value?.toIntOrNull() ?: 0

        // P5
        val p5Name = p5Score.name.value ?: ""
        val p5PlayerId = getPlayerId(p5Name)
        val p5Birds = p5Score.birds.value?.toIntOrNull() ?: 0
        val p5Bonus = p5Score.bonus.value?.toIntOrNull() ?: 0
        val p5Round = p5Score.round.value?.toIntOrNull() ?: 0
        val p5Eggs = p5Score.eggs.value?.toIntOrNull() ?: 0
        val p5Food = p5Score.food.value?.toIntOrNull() ?: 0
        val p5Tucked = p5Score.tucked.value?.toIntOrNull() ?: 0
        val p5Total = p5Score.total.value?.toIntOrNull() ?: 0

        // 順位算出
        val p1Rank = getRank(p1Total, p2Total, p3Total, p4Total, p5Total)
        val p2Rank = getRank(p2Total, p1Total, p3Total, p4Total, p5Total)
        val p3Rank = getRank(p3Total, p1Total, p2Total, p4Total, p5Total)
        val p4Rank = getRank(p4Total, p1Total, p2Total, p3Total, p5Total)
        val p5Rank = getRank(p5Total, p1Total, p2Total, p3Total, p4Total)

        // Score登録
        insertScore(
            p1PlayerId,
            p1Birds,
            p1Bonus,
            p1Round,
            p1Eggs,
            p1Food,
            p1Tucked,
            p1Total,
            p1Rank,
            historyId
        )
        insertScore(
            p2PlayerId,
            p2Birds,
            p2Bonus,
            p2Round,
            p2Eggs,
            p2Food,
            p2Tucked,
            p2Total,
            p2Rank,
            historyId
        )
        insertScore(
            p3PlayerId,
            p3Birds,
            p3Bonus,
            p3Round,
            p3Eggs,
            p3Food,
            p3Tucked,
            p3Total,
            p3Rank,
            historyId
        )
        insertScore(
            p4PlayerId,
            p4Birds,
            p4Bonus,
            p4Round,
            p4Eggs,
            p4Food,
            p4Tucked,
            p4Total,
            p4Rank,
            historyId
        )
        insertScore(
            p5PlayerId,
            p5Birds,
            p5Bonus,
            p5Round,
            p5Eggs,
            p5Food,
            p5Tucked,
            p5Total,
            p5Rank,
            historyId
        )
    }

    val p1Score = InputScore()
    val p2Score = InputScore()
    val p3Score = InputScore()
    val p4Score = InputScore()
    val p5Score = InputScore()

    /**
     * 順位を算出する。
     */
    private fun getRank(myTotal: Int, p2Total: Int, p3Total: Int, p4Total: Int, p5Total: Int): Int {
        val list = listOf(myTotal, p2Total, p3Total, p4Total, p5Total)
        val sorted = list.sortedDescending()
        return sorted.indexOf(myTotal) + 1
    }

    /**
     * 入力内容をクリア
     */
    fun clearInputScores() {
        clearInputScore(p1Score)
        clearInputScore(p2Score)
        clearInputScore(p3Score)
        clearInputScore(p4Score)
        clearInputScore(p5Score)
    }

    /**
     * 入力スコアの内容をクリア
     */
    private fun clearInputScore(score: InputScore) {
        score.birds.value = ""
        score.bonus.value = ""
        score.round.value = ""
        score.eggs.value = ""
        score.food.value = ""
        score.tucked.value = ""
    }

    /**
     * 履歴IDを取得(登録)
     */
    private fun getHistoryId(): Long {
        var historyId = -1L
        runBlocking {
            historyId = repository.insertHistory(History(null))
        }
        return historyId
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