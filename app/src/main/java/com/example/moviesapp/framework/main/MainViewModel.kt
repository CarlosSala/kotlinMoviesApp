package com.example.moviesapp.framework.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.usecases.LoadPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase
) : ViewModel() {

    private val _progressVisible = MutableStateFlow(false)
    val progressVisible: StateFlow<Boolean> get() = _progressVisible

    private val _listPopularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val listPopularMovies: StateFlow<List<Movie>> get() = _listPopularMovies

    private val _showMessage = MutableStateFlow("")
    val showMessage: StateFlow<String> get() = _showMessage

    fun requestPopularMovies(region: String) {
        viewModelScope.launch {
            _progressVisible.value = true
            loadPopularMoviesUseCase(region).collect {
                _listPopularMovies.value = it
            }
            _progressVisible.value = false
        }
    }

    fun onMovieClicked(movie: Movie) {

        _showMessage.value = movie.title
    }
}