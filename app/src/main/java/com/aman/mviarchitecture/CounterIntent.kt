package com.aman.mviarchitecture

sealed class CounterIntent {
    object Increment:CounterIntent()
    object Decrement:CounterIntent()
}