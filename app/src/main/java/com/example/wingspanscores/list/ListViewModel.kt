package com.example.wingspanscores.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.wingspanscores.AppRepository
import com.example.wingspanscores.room.PlayersForList

class ListViewModel(repository: AppRepository) : ViewModel() {
    val players: LiveData<List<PlayersForList>> = repository.playersForList.asLiveData()
}

class ListViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}