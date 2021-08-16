package com.example.firebaseauthentication.domain.interactor.usecase.movies

import com.example.firebaseauthentication.data.repository.MoviesRepository
import com.example.firebaseauthentication.domain.entity.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {

    suspend fun execute(result: Result) =
        moviesRepository.insertMovies(result)
}