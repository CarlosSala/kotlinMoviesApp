package com.example.moviesapp.data.model

import com.example.moviesapp.domain.model.Movie

data class MovieDbResult (
    val page :Int,
    val results :List<Movie>,
    val total_pages :Int,
    val total_results :Int
)