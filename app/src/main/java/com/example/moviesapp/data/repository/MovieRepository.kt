package com.example.moviesapp.data.repository

import com.example.moviesapp.data.repository.network.MoviesService
import com.example.moviesapp.domain.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    // private val remoteDataSource: RemoteDataSource
    private val api: MoviesService
) {

    // someone it is going to use this method and I am going to use this result
    /*suspend fun getMovies(region: String): List<Movie> {

        return remoteDataSource.getPopularMovies(region)
    }*/

    suspend fun getAllMoviesFromApi(region: String): List<Movie> {
        val response: List<Movie> = api.getPopularMovies(region)
        return response
    }

}