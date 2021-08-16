package com.example.firebaseauthentication.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentication.domain.interactor.usecase.movies.DeleteSavedMoviesUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.movies.GetMoviesUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.movies.GetSavedMoviesUseCase
import com.example.firebaseauthentication.domain.interactor.usecase.movies.SaveMoviesUseCase
import com.example.firebaseauthentication.domain.entity.Result
import com.example.firebaseauthentication.utils.ERROR

import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedMoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase
) : ViewModel() {

    private val moviesNews: MutableLiveData<List<Result>> = MutableLiveData()
    private val errorStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        val response = getMoviesUseCase.execute()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue(ERROR)
            return@launch
        }
        moviesNews.postValue( response.body()!!.results)
    }

    fun saveArticle(movie: Result) = viewModelScope.launch {
        saveMoviesUseCase.execute(movie)
    }

    fun getSavedMovies() = getSavedMoviesUseCase.execute()

    fun deleteArticle(movie: Result) = viewModelScope.launch {
        deleteSavedMoviesUseCase.execute(movie)
    }
}