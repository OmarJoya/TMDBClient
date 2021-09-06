package com.ojoya.tmdbclient.data.db

import androidx.room.*
import com.ojoya.tmdbclient.data.model.artist.Artist
import com.ojoya.tmdbclient.data.model.artist.ArtistList

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artists: List<Artist>)

    @Query("SELECT * FROM Artist")
    fun getArtists(): List<Artist>

    @Query("DELETE FROM Artist")
    fun deleteAllArtists()

    @Delete
    fun deleteArtist(artist: Artist)

    @Update
    fun updateArtists(artist: List<Artist>)
}