package com.ojoya.tmdbclient.data.repository.artist.datasource

import com.ojoya.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}