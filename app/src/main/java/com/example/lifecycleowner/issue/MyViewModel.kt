package com.example.lifecycleowner.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class MyViewModel : ViewModel() {

    private val mutableState = MutableLiveData<String>() //or StateFlow
    val state: LiveData<String> = mutableState

    fun initialize() {
        flow {
            //Works with delay(1) or changing dispatcher
            emit("first")
            emit("second")
        }
            .onEach { mutableState.value = it }
            .launchIn(viewModelScope) //Works with Dispatchers.Main

    }
}