package com.ojoya.tmdbclient.presentation.di.tvshow

import com.ojoya.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.ojoya.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.ojoya.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun providesTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory = TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)
}