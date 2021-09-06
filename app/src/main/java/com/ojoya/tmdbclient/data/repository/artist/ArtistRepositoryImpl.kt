package com.ojoya.tmdbclient.data.repository.artist

import android.util.Log
import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.ojoya.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {

    override suspend fun getArtists(): List<Artist> = getArtistsFromCache()

    override suspend fun updateArtists(): List<Artist> {
        val newArtists = getArtistsFromAPI()
        artistLocalDataSource.deleteArtists()
        artistLocalDataSource.saveArtistsToDB(newArtists)
        artistCacheDataSource.saveArtistsToCache(newArtists)
        return newArtists
    }


    private suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            body?.let {
                artistList = it.artists
            }
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return artistList
    }

    private suspend fun getArtistsFromDB(): List<Artist> {
        var artistList: List<Artist> = ArrayList()
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return if (artistList.isNotEmpty()) {
            artistList
        } else {
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistList)
            artistList
        }
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return if (artistList.isNotEmpty()) {
            artistList
        } else {
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
            artistList
        }
    }


}