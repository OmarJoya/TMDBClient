package com.ojoya.tmdbclient.presentation.di.core

import com.ojoya.tmdbclient.data.db.ArtistDao
import com.ojoya.tmdbclient.data.db.MovieDao
import com.ojoya.tmdbclient.data.db.TvShowDao
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasourceimpl.ArtistLocalDataSourceImpl
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun providesMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao)

    @Singleton
    @Provides
    fun providesArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource =
        ArtistLocalDataSourceImpl(artistDao)

    @Singleton
    @Provides
    fun providesTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource =
        TvShowLocalDataSourceImpl(tvShowDao)
}