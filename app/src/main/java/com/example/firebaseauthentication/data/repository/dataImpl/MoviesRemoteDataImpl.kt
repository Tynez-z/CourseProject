package com.example.firebaseauthentication.data.repository.dataImpl

import com.example.firebaseauthentication.data.remote.api.APIInterface
import com.example.firebaseauthentication.domain.entity.MoviesResponse
import com.example.firebaseauthentication.data.repository.dataSource.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataImpl (private val moviesAPI: APIInterface) : MoviesRemoteDataSource {

    override suspend fun getMovies(): Response<MoviesResponse> =
        moviesAPI.getMovies()
}