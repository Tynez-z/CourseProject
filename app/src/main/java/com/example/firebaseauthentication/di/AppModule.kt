package com.example.firebaseauthentication.di

import android.content.Context
import com.example.firebaseauthentication.data.FireBaseSource
import com.example.firebaseauthentication.data.RegisterRepository
import com.example.firebaseauthentication.data.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context = context

    @Singleton
    @Provides
    fun provideRepository(fireBaseSource: FireBaseSource): RegisterRepository {
        return Repository(fireBaseSource)
    }
}