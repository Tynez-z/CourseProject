package com.example.firebaseauthentication.presentation.di

import android.content.Context
import com.example.firebaseauthentication.data.FireBaseSource
import com.example.firebaseauthentication.data.repository.MoviesRepository
import com.example.firebaseauthentication.data.repository.MoviesRepositoryImpl
import com.example.firebaseauthentication.data.repository.RegisterRepository
import com.example.firebaseauthentication.data.repository.RepositoryLogin
import com.example.firebaseauthentication.data.repository.dataSource.MoviesLocalDataSource
import com.example.firebaseauthentication.data.repository.dataSource.MoviesRemoteDataSource
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
    fun provideRepositoryFireBase(fireBaseSource: FireBaseSource): RegisterRepository {
        return RepositoryLogin(fireBaseSource)
    }

    @Singleton
    @Provides
    fun provideRepositoryMovie(moviesRemoteDataSource: MoviesRemoteDataSource, moviesLocalDataSource: MoviesLocalDataSource): MoviesRepository =
        MoviesRepositoryImpl(moviesLocalDataSource, moviesRemoteDataSource)
}