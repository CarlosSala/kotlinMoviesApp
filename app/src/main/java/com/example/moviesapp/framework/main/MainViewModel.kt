package com.example.moviesapp.framework.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.usecases.LoadPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val loadPopularMoviesUseCase = LoadPopularMoviesUseCase(
        MovieRepository(ServerMovieDataSource())
    )

    private val _progressVisible = MutableLiveData<Boolean>()

    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _listPopularMovies = MutableLiveData<List<Movie>>()

    val listPopularMovies: LiveData<List<Movie>> get() = _listPopularMovies

    fun requestPopularMovies(region: String) {

        viewModelScope.launch(Dispatchers.Main) {
            _progressVisible.value = true
            _listPopularMovies.value = loadPopularMoviesUseCase.loadPopularMovies(region)
            _progressVisible.value = false
        }
    }
}