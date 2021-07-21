package com.example.firebaseauthentication.app

import android.app.Application
import com.example.firebaseauthentication.di.AppComponent
import com.example.firebaseauthentication.di.AppModule
import com.example.firebaseauthentication.di.DaggerAppComponent
import com.example.firebaseauthentication.di.FireBaseModule

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
            .fireBaseModule(FireBaseModule())
            .build()
    }
}