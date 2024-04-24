package com.example.moviesapp.framework.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.domain.model.Movie


// this class receive a click event throw a interface
interface MovieClickListener {

    // instead use a lambda function
    fun onMovieClicked(movie: Movie) // (movie) -> Unit
}

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

    // this fun is called by each new item in the recycler view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = listMovies[position]
        holder.bind(movie)

        // when it has occurred a click, it is call onMovieClicked and it passed the movie
        holder.itemView.setOnClickListener {
            onMovieClicked(movie)
        }
    }

    // quantity of items in the recycler view
    override fun getItemCount(): Int {
        return listMovies.size
    }
}