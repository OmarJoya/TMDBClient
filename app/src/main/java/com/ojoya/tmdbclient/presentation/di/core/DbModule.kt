package com.ojoya.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.ojoya.tmdbclient.data.db.ArtistDao
import com.ojoya.tmdbclient.data.db.MovieDao
import com.ojoya.tmdbclient.data.db.TMDBRoomDb
import com.ojoya.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun providesTMDBRoomDb(context: Context): TMDBRoomDb = Room
        .databaseBuilder(context, TMDBRoomDb::class.java, "tmdb")
        .build()

    @Singleton
    @Provides
    fun providesMovieDao(tmdbRoomDb: TMDBRoomDb): MovieDao = tmdbRoomDb.movieDao()

    @Singleton
    @Provides
    fun providesArtistDao(tmdbRoomDb: TMDBRoomDb): ArtistDao = tmdbRoomDb.artistDao()

    @Singleton
    @Provides
    fun providesTvShowDao(tmdbRoomDb: TMDBRoomDb): TvShowDao = tmdbRoomDb.tvShowDao()
}