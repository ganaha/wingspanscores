package com.example.wingspanscores.input

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

data class InputScore(
    var name: MutableLiveData<String> = MutableLiveData(""),
    var birds: MutableLiveData<String> = MutableLiveData(""),
    var bonus: MutableLiveData<String> = MutableLiveData(""),
    var round: MutableLiveData<String> = MutableLiveData(""),
    var eggs: MutableLiveData<String> = MutableLiveData(""),
    var food: MutableLiveData<String> = MutableLiveData(""),
    var tucked: MutableLiveData<String> = MutableLiveData(""),
    var total: MediatorLiveData<String> = MediatorLiveData<String>()
) {
    init {
        val sum = Observer<String> {
            total.value = (
                    (birds.value?.toIntOrNull() ?: 0)
                            + (bonus.value?.toIntOrNull() ?: 0)
                            + (round.value?.toIntOrNull() ?: 0)
                            + (eggs.value?.toIntOrNull() ?: 0)
                            + (food.value?.toIntOrNull() ?: 0)
                            + (tucked.value?.toIntOrNull() ?: 0)
                    ).toString()
        }
        total.value = "0"
        total.addSource(birds, sum)
        total.addSource(bonus, sum)
        total.addSource(round, sum)
        total.addSource(eggs, sum)
        total.addSource(food, sum)
        total.addSource(tucked, sum)
    }
}
