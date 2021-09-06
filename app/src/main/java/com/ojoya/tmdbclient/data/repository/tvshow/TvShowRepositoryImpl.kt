package com.ojoya.tmdbclient.data.repository.tvshow

import android.util.Log
import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.data.model.tvshow.TvShow
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.ojoya.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.ojoya.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow> = getTvShowsFromCache()

    override suspend fun updateTvShows(): List<TvShow>? {
        val newTvShows = getTvShowsFromAPI()
        tvShowLocalDataSource.deleteTvShows()
        tvShowLocalDataSource.saveTvShowsToDB(newTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newTvShows)
        return newTvShows
    }

    private suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            body?.let {
                tvShowList = it.tvShows
            }
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return tvShowList
    }

    private suspend fun getTvShowsFromDB(): List<TvShow> {
        var tvShowList: List<TvShow> = ArrayList()
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return if (tvShowList.isNotEmpty()) {
            tvShowList
        } else {
            tvShowList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
            tvShowList
        }
    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            Log.i("TMDBClient", exception.message.toString())
        }
        return if (tvShowList.isNotEmpty()) {
            tvShowList
        } else {
            tvShowList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
            tvShowList
        }
    }
}