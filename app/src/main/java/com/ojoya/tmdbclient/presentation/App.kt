package com.ojoya.tmdbclient.presentation

import android.app.Application
import com.ojoya.tmdbclient.BuildConfig
import com.ojoya.tmdbclient.presentation.di.Injector
import com.ojoya.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.ojoya.tmdbclient.presentation.di.core.*
import com.ojoya.tmdbclient.presentation.di.movie.MovieSubComponent
import com.ojoya.tmdbclient.presentation.di.tvshow.TvShowSubComponent

class App: Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent = appComponent.movieSubComponent().create()

    override fun createArtistSubComponent(): ArtistSubComponent = appComponent.artistSubComponent().create()

    override fun createTvShowSubComponent(): TvShowSubComponent = appComponent.tvShowSubComponent().create()
}