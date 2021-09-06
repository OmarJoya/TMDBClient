package com.ojoya.tmdbclient.presentation.di.core

import com.ojoya.tmdbclient.domain.repository.ArtistRepository
import com.ojoya.tmdbclient.domain.repository.MovieRepository
import com.ojoya.tmdbclient.domain.repository.TvShowRepository
import com.ojoya.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCase = GetMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideUpdateMoviesUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase = UpdateMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetArtistsUseCase(artistRepository: ArtistRepository): GetArtistsUseCase = GetArtistsUseCase(artistRepository)

    @Singleton
    @Provides
    fun provideUpdateArtistsUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase = UpdateArtistsUseCase(artistRepository)


    @Singleton
    @Provides
    fun provideGetTvShowsUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase = GetTvShowsUseCase(tvShowRepository)

    @Singleton
    @Provides
    fun provideUpdateTvShowsUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase = UpdateTvShowsUseCase(tvShowRepository)
}