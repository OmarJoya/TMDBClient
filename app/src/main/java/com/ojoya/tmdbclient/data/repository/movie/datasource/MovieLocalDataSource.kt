package com.ojoya.tmdbclient.data.repository.movie.datasource

import com.ojoya.tmdbclient.data.model.movie.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDB(): List<Movie>
    suspend fun saveMoviesToDB(movies: List<Movie>)
    suspend fun deleteMovies()

}