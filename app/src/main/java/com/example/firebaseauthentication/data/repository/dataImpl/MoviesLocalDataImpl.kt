package com.example.firebaseauthentication.data.repository.dataImpl

import androidx.lifecycle.LiveData
import com.example.firebaseauthentication.data.db.cache.MoviesInterface
import com.example.firebaseauthentication.data.repository.dataSource.MoviesLocalDataSource
import com.example.firebaseauthentication.domain.entity.Result

class MoviesLocalDataImpl(private val movieDao: MoviesInterface) : MoviesLocalDataSource {

    override fun getSavedMovies(): LiveData<List<Result>> =
        movieDao.getAllMovies()

    override suspend fun insertMovies(movies: Result) =
        movieDao.insertMovies(movies)

    override suspend fun deleteMovies(movies: Result) =
        movieDao.deleteMovies(movies)
}