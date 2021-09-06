package com.ojoya.tmdbclient.data.repository.tvshow.datasource

import com.ojoya.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}