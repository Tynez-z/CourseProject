package com.example.firebaseauthentication

import com.example.firebaseauthentication.data.remote.api.APIInterface
import com.example.firebaseauthentication.data.remote.api.MoviesAPI
import com.example.firebaseauthentication.data.repository.MoviesRepository
import com.example.firebaseauthentication.data.repository.dataImpl.MoviesRemoteDataImpl
import com.example.firebaseauthentication.data.repository.dataSource.MoviesRemoteDataSource
import com.example.firebaseauthentication.domain.entity.MoviesResponse
import com.example.firebaseauthentication.domain.interactor.usecase.movies.GetMoviesUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import retrofit2.Response

@RunWith(MockitoJUnitRunner ::class)
class TestMocky () {

//    @get:Rule
//    val mockitoRule : MockitoRule = MockitoJUnit.rule()
//
//    @Mock
//    lateinit var apiInterface: APIInterface
//
//    lateinit var  movieRemoteDataSource : MoviesRemoteDataSource
//    lateinit var getMoviesUseCase: GetMoviesUseCase
//
//
//    @Before
//    fun setUp () {
//        getMoviesUseCase = GetMoviesUseCase()
//        movieRemoteDataSource = MoviesRemoteDataImpl(apiInterface)
//    }
//
//    fun checkApiKey () {
//
//    }
//
//    @Test
//    suspend fun getMovies(): Response<MoviesResponse> {
//
//        val expectedArgument = moviesAPI.getMovies("cfacbd1b17a84295a04a55d573daa740")
//
//        Mockito.`when`(moviesAPI.getMovies(Mockito.anyString())).thenReturn(expectedArgument)
//        return expectedArgument
//    }
}