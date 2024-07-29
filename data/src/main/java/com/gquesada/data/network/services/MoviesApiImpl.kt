package com.gquesada.data.network.services

import com.gquesada.data.network.model.NetworkMovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.parameter
import javax.inject.Inject

class MoviesApiImpl @Inject constructor(
    private val client: HttpClient
) : MoviesApi {

    override suspend fun getMovieList(page: Int): NetworkMovieResponse {
        val url = URLServices.Movies.DISCOVER
        return client.get(path = url) {
            parameter("include_adult", false)
            parameter("include_video", false)
            parameter("language", "en-US")
            parameter("page", page)
            parameter("sort_by", "popularity.desc")
        }
    }
}