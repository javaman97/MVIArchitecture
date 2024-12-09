package com.aman.mviarchitecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel:ViewModel(){

    private val _state = MutableStateFlow<CounterState>(CounterState.Value(0))
    val state: StateFlow<CounterState> = _state

    fun processIntent(intent: CounterIntent){
        when(intent){

            is CounterIntent.Increment -> {
                val currentCount = (state.value as CounterState.Value).count
                _state.update { CounterState.Value(currentCount+1) }
            }

            is CounterIntent.Decrement ->{
                val currentCount = (state.value as CounterState.Value).count
                _state.update { CounterState.Value(currentCount-1)  }

            }

        }
    }

}