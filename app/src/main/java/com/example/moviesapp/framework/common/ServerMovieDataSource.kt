package com.example.moviesapp.framework.common

import com.example.moviesapp.data.repository.network.MovieDbClient
import com.example.moviesapp.data.repository.network.RemoteDataSource
import com.example.moviesapp.domain.model.Movie

class ServerMovieDataSource: RemoteDataSource {

    // this result it will be used by other who will be using this
    override suspend fun getPopularMovies(region: String): List<Movie> {

        val popularMovies = MovieDbClient.service.getPopularMovies(
            "791a8c4d026076d931801fd25a0f9343",
            region
        )
        return popularMovies.results
    }
}