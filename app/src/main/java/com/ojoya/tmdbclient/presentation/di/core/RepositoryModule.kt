package com.ojoya.tmdbclient.presentation.di.core

import com.ojoya.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.ojoya.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.ojoya.tmdbclient.domain.repository.ArtistRepository
import com.ojoya.tmdbclient.domain.repository.MovieRepository
import com.ojoya.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource)

    @Singleton
    @Provides
    fun providesTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository =
        TvShowRepositoryImpl(tvShowRemoteDataSource, tvShowLocalDataSource, tvShowCacheDataSource)

    @Singleton
    @Provides
    fun providesArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository =
        ArtistRepositoryImpl(artistRemoteDataSource, artistLocalDataSource, artistCacheDataSource)
}