package com.ojoya.tmdbclient.presentation.di.core

import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasourceimpl.ArtistCacheDataSourceImpl
import com.ojoya.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.ojoya.tmdbclient.data.repository.movie.datasourceimpl.MovieCacheDataSourceImpl
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun providesMovieCacheDataSource(): MovieCacheDataSource = MovieCacheDataSourceImpl()

    @Singleton
    @Provides
    fun providesArtistCacheDataSource(): ArtistCacheDataSource = ArtistCacheDataSourceImpl()

    @Singleton
    @Provides
    fun providesTvShowCacheDataSource(): TvShowCacheDataSource = TvShowCacheDataSourceImpl()
}