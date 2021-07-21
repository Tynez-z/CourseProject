package com.example.firebaseauthentication.di

import com.example.firebaseauthentication.ui.fragments.BaseFragment
import com.example.firebaseauthentication.ui.fragments.SplashScreenFragment
import com.example.firebaseauthentication.ui.fragments.DashBoardFragment
import com.example.firebaseauthentication.ui.fragments.LoginFragment
import com.example.firebaseauthentication.ui.fragments.SignUpFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FireBaseModule::class, ViewModelModule::class])

interface AppComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(baseFragment: BaseFragment)
    fun inject(dashBoardFragment: DashBoardFragment)
    fun inject(splashScreenFragment: SplashScreenFragment)
}