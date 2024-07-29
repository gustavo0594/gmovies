package com.gquesada.data.di

import com.gquesada.data.network.services.MoviesApi
import com.gquesada.data.network.services.MoviesApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    abstract fun bindMoviesApi(moviesApi: MoviesApiImpl): MoviesApi
}
