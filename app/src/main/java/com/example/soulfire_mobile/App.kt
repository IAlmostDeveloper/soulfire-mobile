package com.example.soulfire_mobile


import android.app.Application
import org.androidannotations.annotations.EApplication


@EApplication
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        var instance: App? = null
            private set
    }
}