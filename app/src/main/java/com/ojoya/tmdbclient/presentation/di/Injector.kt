package com.ojoya.tmdbclient.presentation.di

import com.ojoya.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.ojoya.tmdbclient.presentation.di.movie.MovieSubComponent
import com.ojoya.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
}