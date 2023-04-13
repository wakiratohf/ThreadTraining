package com.dos.threadtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private val TAG = "ThreadTraining"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doWork()
        doWorkWithCoroutines()
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