package com.ojoya.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ojoya.tmdbclient.domain.usecase.GetMoviesUseCase
import com.ojoya.tmdbclient.domain.usecase.UpdateMoviesUseCase

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMovieUseCase: UpdateMoviesUseCase
): ViewModel() {

    fun getMovies() = liveData {
        val movies = getMoviesUseCase.execute()
        emit(movies)
    }

    fun updateMovies() = liveData {
        val movies = updateMovieUseCase.execute()
        emit(movies)
    }
}