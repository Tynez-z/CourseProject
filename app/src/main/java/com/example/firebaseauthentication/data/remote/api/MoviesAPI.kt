package com.example.firebaseauthentication.data.remote.api

import com.example.firebaseauthentication.domain.entity.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI : APIInterface {

    companion object {
        const val GET_MOVIES = "/3/movie/popular"
        const val API_KEY = "api_key"
    }

    @GET(GET_MOVIES)
    override suspend fun getMovies(@Query(API_KEY) apiKey: String): Response<MoviesResponse>
}