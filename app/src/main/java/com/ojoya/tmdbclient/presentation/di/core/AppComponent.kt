package com.ojoya.tmdbclient.presentation.di.core

import com.ojoya.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.ojoya.tmdbclient.presentation.di.movie.MovieSubComponent
import com.ojoya.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        DbModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        LocalDataModule::class,
        RemoteDataModule::class,
        CacheDataModule::class
    ]
)
interface AppComponent {
    fun movieSubComponent(): MovieSubComponent.Factory
    fun tvShowSubComponent(): TvShowSubComponent.Factory
    fun artistSubComponent(): ArtistSubComponent.Factory
}