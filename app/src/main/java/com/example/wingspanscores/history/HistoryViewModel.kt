package com.example.wingspanscores.history

import androidx.lifecycle.*
import com.example.wingspanscores.AppRepository
import com.example.wingspanscores.room.HistoryWithScores

class HistoryViewModel(repository: AppRepository) : ViewModel() {
    val histories: LiveData<List<HistoryWithScores>> = repository.histories.asLiveData()
}

class HistoryViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}