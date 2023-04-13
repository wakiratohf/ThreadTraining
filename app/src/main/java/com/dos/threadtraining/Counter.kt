package com.dos.threadtraining

class Counter {
    private var count = 0

    @Synchronized fun increment(){
        count++
    }

    @Synchronized fun getCount() : Int{
        return count
    }
}