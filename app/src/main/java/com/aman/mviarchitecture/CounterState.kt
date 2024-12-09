package com.aman.mviarchitecture

sealed class CounterState {
    data class Value(val count: Int):CounterState()
}