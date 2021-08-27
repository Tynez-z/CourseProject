package com.example.firebaseauthentication

import android.app.Application
import com.example.firebaseauthentication.presentation.di.*
import com.example.firebaseauthentication.utils.createNotificationsChanel

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

        createNotificationsChanel()
    }
}