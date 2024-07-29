package com.gquesada.data.datasources

import com.gquesada.data.network.model.NetworkMovieResponse
import com.gquesada.data.network.services.MoviesApi
import javax.inject.Inject

class MovieNetworkDatasource @Inject constructor(
    private val api: MoviesApi
) {

    suspend fun getMovies(page: Int): NetworkMovieResponse {
        return api.getMovieList(page)
    }
}