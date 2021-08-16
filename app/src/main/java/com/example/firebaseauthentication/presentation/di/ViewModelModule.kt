package com.example.firebaseauthentication.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.presentation.viewmodel.LoginViewModel
import com.example.firebaseauthentication.presentation.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.firebaseauthentication.MoviesViewModelProviderFactory
import com.example.firebaseauthentication.ViewModelKey
import com.example.firebaseauthentication.presentation.viewmodel.ArticleViewModel
import com.example.firebaseauthentication.presentation.viewmodel.MoviesViewModel
import com.example.firebaseauthentication.presentation.viewmodel.SavedMoviesViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: MoviesViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleViewModel(viewModel: ArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SavedMoviesViewModel::class)
    abstract fun bindSavedMoviesViewModel(viewModel: SavedMoviesViewModel): ViewModel
}