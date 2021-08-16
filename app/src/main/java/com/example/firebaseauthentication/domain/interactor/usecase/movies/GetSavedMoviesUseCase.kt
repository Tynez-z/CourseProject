package com.example.firebaseauthentication.domain.interactor.usecase.movies

import androidx.lifecycle.LiveData
import com.example.firebaseauthentication.data.repository.MoviesRepository
import com.example.firebaseauthentication.domain.entity.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSavedMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {

    fun execute(): LiveData<List<Result>> =
        moviesRepository.getSavedMovies()
}