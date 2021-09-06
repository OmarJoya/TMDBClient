package com.ojoya.tmdbclient.domain.usecase

import com.ojoya.tmdbclient.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute() = movieRepository.updateMovies()
}