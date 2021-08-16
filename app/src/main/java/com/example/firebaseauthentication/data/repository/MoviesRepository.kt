package com.example.firebaseauthentication.data.repository

import androidx.lifecycle.LiveData
import com.example.firebaseauthentication.domain.entity.MoviesResponse
import com.example.firebaseauthentication.domain.entity.Result
import retrofit2.Response

interface MoviesRepository {
    fun getSavedMovies() : LiveData<List<Result>>
    suspend fun getMovies(): Response<MoviesResponse>
    suspend fun insertMovies(movies: Result)
    suspend fun deleteMovies(movies: Result)
}