package com.ojoya.tmdbclient.data.db

import androidx.room.*
import com.ojoya.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShow>)

    @Query("SELECT * FROM TvShow")
    fun getTvShows(): List<TvShow>

    @Query("DELETE FROM TvShow")
    fun deleteAllTvShows()

    @Delete
    fun deleteTvShow(tvShow: TvShow)

    @Update
    fun updateTvShow(tvShow: TvShow)
}