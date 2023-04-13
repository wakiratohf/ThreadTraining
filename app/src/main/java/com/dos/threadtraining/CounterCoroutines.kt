package com.dos.threadtraining

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class CounterCoroutines {
    private val channel = Channel<Int> { Channel.UNLIMITED } // can set capacity
    private var count = 0

    init {
        GlobalScope.launch {
            for (increment in channel) {
                count += increment
            }
        }
    }

    suspend fun increment() {
        channel.send(1)
    }

    fun getCount(): Int {
        return count
    }

    fun close() {
        channel.close()
    }
}