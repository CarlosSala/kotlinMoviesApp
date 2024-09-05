package com.example.moviesapp.data.repository.network

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val apiClient: MovieApiClient
) {

    // this result it will be used by other who will be using this
    suspend fun getPopularMovies(region: String): List<Movie> {

        val popularMovies = withContext(Dispatchers.IO) {
            apiClient.getPopularMovies(
                BuildConfig.API_KEY_SAFE,
                region
            )
        }
        return popularMovies.results
    }
}