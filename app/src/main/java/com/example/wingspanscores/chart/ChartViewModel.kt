package com.example.wingspanscores.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.wingspanscores.AppRepository
import com.example.wingspanscores.room.ScoreByPlayer

class ChartViewModel(private val repository: AppRepository) : ViewModel() {
    fun getScoresByPlayer(name: String): LiveData<List<ScoreByPlayer>> {
        return repository.getScoresByPlayer(name).asLiveData()
    }
}

class ChartViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}