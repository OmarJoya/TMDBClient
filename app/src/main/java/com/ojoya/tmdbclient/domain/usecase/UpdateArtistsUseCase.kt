package com.ojoya.tmdbclient.domain.usecase

import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.domain.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist> = artistRepository.updateArtists()
}