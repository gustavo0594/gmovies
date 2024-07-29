package com.gquesada.data.repositories

import com.gquesada.data.datasources.MovieNetworkDatasource
import com.gquesada.domain.models.Movie
import com.gquesada.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val netDatasource: MovieNetworkDatasource
) : MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie> {
        return netDatasource.getMovies(page).let { response ->
            response.results.map { movie ->
                Movie(
                    genreIds = movie.genreIds,
                    id = movie.id,
                    originalLanguage = movie.originalLanguage,
                    originalTitle = movie.originalTitle,
                    overview = movie.overview,
                    popularity = movie.popularity,
                    posterPath = movie.posterPath,
                    releaseDate = movie.releaseDate,
                    title = movie.title,
                    video = movie.video,
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount
                )
            }
        }

    }
}