package com.gquesada.domain.repositories

import com.gquesada.domain.models.Movie

interface MovieRepository {

    suspend fun getMovies(page:Int): List<Movie>
}