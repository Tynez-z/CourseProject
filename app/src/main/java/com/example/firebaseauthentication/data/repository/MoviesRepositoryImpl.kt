package com.example.firebaseauthentication.data.repository

import androidx.lifecycle.LiveData
import com.example.firebaseauthentication.data.repository.dataSource.MoviesLocalDataSource
import com.example.firebaseauthentication.data.repository.dataSource.MoviesRemoteDataSource
import com.example.firebaseauthentication.domain.entity.MoviesResponse
import com.example.firebaseauthentication.domain.entity.Result
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun getMovies(): Response<MoviesResponse> =
        moviesRemoteDataSource.getMovies()

    override suspend fun insertMovies(movies: Result) =
        moviesLocalDataSource.insertMovies(movies)

    override suspend fun deleteMovies(movies: Result) =
        moviesLocalDataSource.deleteMovies(movies)

    override fun getSavedMovies(): LiveData<List<Result>> =
        moviesLocalDataSource.getSavedMovies()
}