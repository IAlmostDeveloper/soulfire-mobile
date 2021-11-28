package com.example.soulfire_mobile.reuse

abstract class CancelableRunnable : Runnable {
    protected var mCancelled = false
    fun cancel() {
        mCancelled = true
        onCancelled()
    }

    abstract fun onCancelled()
}