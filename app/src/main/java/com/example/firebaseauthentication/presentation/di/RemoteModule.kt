package com.example.firebaseauthentication.presentation.di

import com.example.firebaseauthentication.BuildConfig.BASE_URL
import com.example.firebaseauthentication.data.remote.api.APIInterface
import com.example.firebaseauthentication.data.remote.api.MoviesAPI
import com.example.firebaseauthentication.data.repository.dataImpl.MoviesRemoteDataImpl
import com.example.firebaseauthentication.data.repository.dataSource.MoviesRemoteDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIInterface(retrofit: Retrofit): APIInterface =
        retrofit.create(MoviesAPI::class.java)

    @Singleton
    @Provides
    fun providesRemoteMovieDataSource(service: APIInterface): MoviesRemoteDataSource =
        MoviesRemoteDataImpl(service)
}