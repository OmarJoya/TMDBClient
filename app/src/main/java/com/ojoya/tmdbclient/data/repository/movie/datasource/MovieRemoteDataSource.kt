package com.ojoya.tmdbclient.data.repository.movie.datasource

import com.ojoya.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}