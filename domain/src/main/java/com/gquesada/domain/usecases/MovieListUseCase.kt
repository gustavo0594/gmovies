package com.gquesada.domain.usecases

import com.gquesada.domain.models.Movie
import com.gquesada.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(input: MovieListUseCaseInput): List<Movie> {
        return movieRepository.getMovies(input.pageNumber)
    }
}

data class MovieListUseCaseInput(
    val pageNumber: Int
)