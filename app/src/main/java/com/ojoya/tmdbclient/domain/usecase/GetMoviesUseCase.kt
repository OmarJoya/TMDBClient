package com.ojoya.tmdbclient.domain.usecase

import com.ojoya.tmdbclient.data.model.movie.Movie
import com.ojoya.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}