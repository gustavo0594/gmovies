package com.gquesada.data.network.services

import com.gquesada.data.network.model.NetworkMovieResponse

interface MoviesApi {

    suspend fun getMovieList(page: Int): NetworkMovieResponse
}