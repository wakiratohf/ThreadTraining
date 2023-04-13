package com.dos.threadtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import java.lang.Runnable
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val TAG = "ThreadTraining-LOG"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doWork()
        doWorkWithCoroutines()
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                doCounterExample()
            }
        }
    }

    private suspend fun doCounterExample() {
        val counter = CounterCoroutines()
        val job1 = GlobalScope.launch {
            repeat(1000) {
                counter.increment()
                Log.d(TAG, "Thread 1: " + counter.getCount());
            }
        }
        val job2 = GlobalScope.launch {
            repeat(1000) {
                counter.increment()
                Log.w(TAG, "Thread 2: " + counter.getCount());
            }
        }
        job1.join()
        job2.join()
        Log.i(TAG, "Final count: ${counter.getCount()}");
    }

    private fun doWorkWithCoroutines(): Job {
        return runBlocking {
            launch {
                Log.i(TAG, "Work with Coroutines running");
            }
        }
    }

    private fun doWork() {
        val data = "Hello, world!"
        val thread = Thread(Runnable {
            println(data)
        })
        thread.start()
    }
}