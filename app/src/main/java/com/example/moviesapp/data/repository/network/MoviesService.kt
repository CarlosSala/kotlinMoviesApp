package com.example.moviesapp.data.repository.network

import com.example.moviesapp.domain.model.Movie
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val apiClient: MovieApiClient
) {

    // this result it will be used by other who will be using this
    suspend fun getPopularMovies(region: String): List<Movie> {

        val popularMovies = apiClient.getPopularMovies(
            "791a8c4d026076d931801fd25a0f9343",
            region
        )
        return popularMovies.results
    }
}