package com.dos.threadtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val TAG = "ThreadTraining-LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doWork()
        doWorkWithCoroutines()
        doCounterExample()
    }

    private fun doCounterExample() {
        val counter = Counter()
        val thread1 = Thread {
            for (i in 1..1000) {
                counter.increment()
                Log.d(TAG, "Thread 1: " + counter.getCount());
            }
        }
        val thread2 = Thread {
            for (i in 1..1000) {
                counter.increment()
                Log.w(TAG, "Thread 2: " + counter.getCount());
            }
        }
        thread1.start()
        thread2.start()
        thread1.join()
        thread2.join()

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