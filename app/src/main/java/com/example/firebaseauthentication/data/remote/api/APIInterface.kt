package com.example.firebaseauthentication.data.remote.api

import com.example.firebaseauthentication.BuildConfig.API_KEY
import com.example.firebaseauthentication.domain.entity.MoviesResponse
import retrofit2.Response

interface APIInterface {
    suspend fun getMovies(apiKey: String = API_KEY): Response<MoviesResponse>
}