package com.example.firebaseauthentication.data.db.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebaseauthentication.domain.entity.Result

@Dao
interface MoviesDao : MoviesInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertMovies(movies: Result)

    @Query("SELECT * FROM movies")
    override fun getAllMovies(): LiveData<List<Result>>

    @Delete
    override suspend fun deleteMovies(movies: Result)
}