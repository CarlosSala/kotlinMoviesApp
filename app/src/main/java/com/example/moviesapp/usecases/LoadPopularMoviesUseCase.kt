package com.example.moviesapp.usecases

import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.domain.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(region: String): Flow<List<Movie>> = flow {

        while (true) {
            emit(movieRepository.getAllMoviesFromApi(region).shuffled())
            delay(2000)
        }
    }
}