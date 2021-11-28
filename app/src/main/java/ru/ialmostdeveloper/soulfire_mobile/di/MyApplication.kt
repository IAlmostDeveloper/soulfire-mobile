package ru.ialmostdeveloper.soulfire_mobile.di

import android.app.Application

class MyApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .context(applicationContext)
            ?.appModule(AppModule())
            ?.build()
    }
}