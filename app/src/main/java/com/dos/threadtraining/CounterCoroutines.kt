package com.dos.threadtraining

class CounterCoroutines {
    private var count = 0

    suspend fun increment() {
        count++
    }

    fun getCount() : Int{
        return count
    }
}