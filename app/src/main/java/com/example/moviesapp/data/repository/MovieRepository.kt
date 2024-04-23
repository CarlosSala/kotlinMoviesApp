package com.example.moviesapp.data.repository

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.data.repository.network.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) {

    // someone it is going to use this method and I am going to use this result

    suspend fun getMovies(region:String): List<Movie> {

        return remoteDataSource.getPopularMovies(region)
    }
}