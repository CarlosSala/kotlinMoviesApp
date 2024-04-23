package com.example.moviesapp.framework.detail

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityDetailBinding
import com.example.moviesapp.domain.model.Movie

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_MOVIE, Movie::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_MOVIE)
        }

        if (movie != null) {
            // title of toolbar
            title = movie.title

            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                .into(binding.ivCourseDetail)
            binding.tvSummary.text = movie.overview
            bindDetailInfo(binding.tvDetailInfo, movie)
        }
    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {

        // to work in only a textView playing with text
        detailInfo.text = buildSpannedString {

            appendInfo(R.string.original_language, movie.original_language)
            appendInfo(R.string.original_title, movie.original_title)
            appendInfo(R.string.release_date, movie.release_date)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_average, movie.vote_average.toString())

        }
    }

    // extension function
    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String) {

        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }

}