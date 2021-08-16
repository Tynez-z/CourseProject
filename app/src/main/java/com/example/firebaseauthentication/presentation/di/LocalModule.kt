package com.example.firebaseauthentication.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.firebaseauthentication.data.db.cache.MoviesDao
import com.example.firebaseauthentication.data.db.cache.MoviesDataBase
import com.example.firebaseauthentication.data.repository.dataImpl.MoviesLocalDataImpl
import com.example.firebaseauthentication.data.repository.dataSource.MoviesLocalDataSource
import com.example.firebaseauthentication.utils.NAME_OF_DB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun providesMovieLocalDataSource(dao: MoviesDao): MoviesLocalDataSource =
        MoviesLocalDataImpl(dao)

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MoviesDataBase =
        Room.databaseBuilder(context, MoviesDataBase::class.java, NAME_OF_DB).build()

    @Singleton
    @Provides
    fun provideMoviesDao(db: MoviesDataBase): MoviesDao =
        db.getMoviesDao()
}