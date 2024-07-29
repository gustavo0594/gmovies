package com.gquesada.data.di

import com.gquesada.data.repositories.MovieRepositoryImpl
import com.gquesada.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMovieRepository(
        movieRepository: MovieRepositoryImpl,
    ): MovieRepository
}