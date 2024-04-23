package com.example.moviesapp.framework.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.domain.model.Movie

class MoviesAdapter(
    private var listMovies: List<Movie>,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    fun setListMovies(listMovies: List<Movie>) {
        this.listMovies = listMovies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = listMovies[position]
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            onMovieClicked(movie)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}