package com.example.firebaseauthentication.domain.interactor.usecase.movies

import com.example.firebaseauthentication.data.repository.MoviesRepository
import com.example.firebaseauthentication.domain.entity.MoviesResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository) {

    suspend fun execute(): Response<MoviesResponse> =
        moviesRepository.getMovies()
}