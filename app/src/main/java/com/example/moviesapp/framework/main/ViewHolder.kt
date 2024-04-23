package com.example.moviesapp.framework.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.domain.model.Movie

class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(movie: Movie) {

        binding.tvCourse.text = movie.title
        Glide
            .with(binding.tvCourse.context)
            .load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
            .into(binding.ivCourse)

    }

}