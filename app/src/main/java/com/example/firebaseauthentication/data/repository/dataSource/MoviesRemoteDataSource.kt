package com.example.firebaseauthentication.data.repository.dataSource

import com.example.firebaseauthentication.domain.entity.MoviesResponse
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getMovies(): Response<MoviesResponse>
}