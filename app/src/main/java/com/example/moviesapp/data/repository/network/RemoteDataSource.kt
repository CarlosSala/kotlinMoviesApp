package com.example.moviesapp.data.repository.network

import com.example.moviesapp.domain.model.Movie

interface RemoteDataSource {

    suspend fun getPopularMovies(region:String): List<Movie>
}