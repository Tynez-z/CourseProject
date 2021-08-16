package com.example.firebaseauthentication.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.firebaseauthentication.domain.entity.Result

interface MoviesLocalDataSource {
    fun getSavedMovies(): LiveData<List<Result>>
    suspend fun insertMovies(movies: Result)
    suspend fun deleteMovies(movies: Result)
}