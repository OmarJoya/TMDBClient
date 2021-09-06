package com.ojoya.tmdbclient.domain.repository

import com.ojoya.tmdbclient.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>

    suspend fun updateArtists(): List<Artist>
}