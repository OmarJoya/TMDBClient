package com.ojoya.tmdbclient.data.repository.movie.datasourceimpl

import com.ojoya.tmdbclient.data.api.TMDBService
import com.ojoya.tmdbclient.data.model.movie.MovieList
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDataSource {

    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}