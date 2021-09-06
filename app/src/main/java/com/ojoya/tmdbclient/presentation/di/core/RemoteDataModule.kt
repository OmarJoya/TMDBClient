package com.ojoya.tmdbclient.presentation.di.core

import com.ojoya.tmdbclient.data.api.TMDBService
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasourceimpl.ArtistRemoteDataSourceImpl
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(tmdbService, apiKey)

    @Singleton
    @Provides
    fun providesArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource =
        ArtistRemoteDataSourceImpl(tmdbService, apiKey)

    @Singleton
    @Provides
    fun providesTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tmdbService, apiKey)

}