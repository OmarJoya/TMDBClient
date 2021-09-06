package com.ojoya.tmdbclient.data.db

import androidx.room.*
import com.ojoya.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getMovies(): List<Movie>

    @Query("DELETE FROM Movie")
    fun deleteAllMovies()

    @Delete
    fun deleteMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)
}