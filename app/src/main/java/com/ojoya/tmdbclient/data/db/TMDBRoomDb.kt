package com.ojoya.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.data.model.movie.Movie
import com.ojoya.tmdbclient.data.model.tvshow.TvShow

@Database(entities = [Artist::class, Movie::class, TvShow::class], version = 1)
abstract class TMDBRoomDb: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun artistDao(): ArtistDao
    abstract fun tvShowDao(): TvShowDao
}