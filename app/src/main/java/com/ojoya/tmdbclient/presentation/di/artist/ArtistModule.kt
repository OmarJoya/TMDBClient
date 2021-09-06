package com.ojoya.tmdbclient.presentation.di.artist

import com.ojoya.tmdbclient.domain.usecase.GetArtistsUseCase
import com.ojoya.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.ojoya.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun providesArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory = ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
}