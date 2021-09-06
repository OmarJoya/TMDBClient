package com.ojoya.tmdbclient.presentation.di.movie

import com.ojoya.tmdbclient.domain.usecase.GetMoviesUseCase
import com.ojoya.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.ojoya.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun providesMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory = MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
}