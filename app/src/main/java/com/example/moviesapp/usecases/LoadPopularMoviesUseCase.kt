package com.example.moviesapp.usecases

import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadPopularMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun loadPopularMovies(region: String): List<Movie> = withContext(Dispatchers.IO) {

        movieRepository.getMovies(region)
    }
}