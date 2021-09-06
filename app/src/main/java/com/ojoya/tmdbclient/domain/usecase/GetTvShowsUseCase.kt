package com.ojoya.tmdbclient.domain.usecase

import com.ojoya.tmdbclient.data.model.tvshow.TvShow
import com.ojoya.tmdbclient.domain.repository.TvShowRepository

class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.getTvShows()
}