package com.example.moviesapp.data.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbClient {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MovieApiClient = retrofit.create(MovieApiClient::class.java)

}