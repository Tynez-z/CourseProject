package com.example.firebaseauthentication.presentation.di

import com.example.firebaseauthentication.presentation.fragments.BaseFragment
import com.example.firebaseauthentication.presentation.fragments.login.SplashScreenFragment
import com.example.firebaseauthentication.presentation.fragments.login.LoginFragment
import com.example.firebaseauthentication.presentation.fragments.login.SignUpFragment
import com.example.firebaseauthentication.presentation.fragments.movies.ArticleFragment
import com.example.firebaseauthentication.presentation.fragments.movies.MoviesFragment
import com.example.firebaseauthentication.presentation.fragments.movies.SavedMoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FireBaseModule::class, ViewModelModule::class, LocalModule::class, RemoteModule::class])

interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(baseFragment: BaseFragment)
    fun inject(moviesFragment: MoviesFragment)
    fun inject(articleFragment: ArticleFragment)
    fun inject(savedMoviesFragment: SavedMoviesFragment)
    fun inject(splashScreenFragment: SplashScreenFragment)
}