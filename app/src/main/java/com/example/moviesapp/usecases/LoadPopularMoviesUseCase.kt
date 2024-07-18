package com.example.moviesapp.usecases

import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadPopularMoviesUseCase @Inject constructor (
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(region: String): List<Movie> = withContext(Dispatchers.IO) {

        movieRepository.getAllMoviesFromApi(region)
    }
}