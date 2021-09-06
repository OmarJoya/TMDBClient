package com.ojoya.tmdbclient.data.repository.artist.datasourceimpl

import com.ojoya.tmdbclient.data.api.TMDBService
import com.ojoya.tmdbclient.data.model.artist.ArtistList
import com.ojoya.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : ArtistRemoteDataSource {

    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)
}