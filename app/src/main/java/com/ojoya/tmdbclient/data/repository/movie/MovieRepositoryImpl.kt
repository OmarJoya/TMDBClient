package com.ojoya.tmdbclient.data.repository.movie

import android.util.Log
import com.ojoya.tmdbclient.data.model.movie.Movie
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.ojoya.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> = getMoviesFromCache()

    override suspend fun updateMovies(): List<Movie>? {
        val newMovies = getMoviesFromAPI()
        movieLocalDataSource.deleteMovies()
        movieLocalDataSource.saveMoviesToDB(newMovies)
        movieCacheDataSource.saveMoviesToCache(newMovies)
        return newMovies
    }

    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            body?.let {
                movieList = it.movies
            }
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return movieList
    }

    private suspend fun getMoviesFromDB(): List<Movie> {
        var movieList: List<Movie> = ArrayList()
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        if(movieList.isNotEmpty()){
            return movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    private suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return if (movieList.isNotEmpty()) {
            movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
            movieList
        }
    }
}