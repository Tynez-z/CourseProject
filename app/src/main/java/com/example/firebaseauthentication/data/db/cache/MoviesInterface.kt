package com.example.firebaseauthentication.data.db.cache

import androidx.lifecycle.LiveData
import com.example.firebaseauthentication.domain.entity.Result

interface MoviesInterface {
    fun getAllMovies(): LiveData<List<Result>>
    suspend fun insertMovies(movies: Result)
    suspend fun deleteMovies(movies: Result)
}