package com.ojoya.tmdbclient.data.repository.tvshow.datasourceimpl

import com.ojoya.tmdbclient.data.db.TvShowDao
import com.ojoya.tmdbclient.data.model.tvshow.TvShow
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> = tvShowDao.getTvShows()

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.insertTvShows(tvShows)
        }
    }

    override suspend fun deleteTvShows() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}