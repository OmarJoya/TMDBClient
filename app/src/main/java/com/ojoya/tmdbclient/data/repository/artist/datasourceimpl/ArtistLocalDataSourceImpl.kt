package com.ojoya.tmdbclient.data.repository.artist.datasourceimpl

import com.ojoya.tmdbclient.data.db.ArtistDao
import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao): ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> = artistDao.getArtists()

    override suspend fun saveArtistsToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.insertArtists(artists)
        }
    }

    override suspend fun deleteArtists() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}