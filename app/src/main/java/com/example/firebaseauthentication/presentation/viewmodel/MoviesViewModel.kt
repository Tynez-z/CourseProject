package com.example.firebaseauthentication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentication.domain.interactor.usecase.movies.GetMoviesUseCase
import com.example.firebaseauthentication.utils.ERROR
import com.example.firebaseauthentication.domain.entity.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    val moviesNews: MutableLiveData<List<Result>> = MutableLiveData()
    val errorStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        val response = getMoviesUseCase.execute()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue(ERROR)
            return@launch
        }

        moviesNews.postValue(response.body()!!.results) //TODO make check (NPE)
    }
}