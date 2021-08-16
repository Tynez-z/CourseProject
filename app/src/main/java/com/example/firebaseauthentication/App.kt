package com.example.firebaseauthentication

import android.app.Application
import com.example.firebaseauthentication.presentation.di.*

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .localModule(LocalModule())
            .remoteModule(RemoteModule())
            .fireBaseModule(FireBaseModule())
            .build()
    }
}