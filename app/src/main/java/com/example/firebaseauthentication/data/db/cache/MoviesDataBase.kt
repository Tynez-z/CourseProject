package com.example.firebaseauthentication.data.db.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firebaseauthentication.domain.entity.Result

@Database(entities = [Result::class], version = 1)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}