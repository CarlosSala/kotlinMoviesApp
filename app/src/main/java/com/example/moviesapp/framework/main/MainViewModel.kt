package com.example.moviesapp.framework.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.usecases.LoadPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadPopularMoviesUseCase: LoadPopularMoviesUseCase
) : ViewModel() {

    /*
    private val loadPopularMoviesUseCase = LoadPopularMoviesUseCase(
        MovieRepository(ServerMovieDataSource())
    )*/

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _listPopularMovies = MutableLiveData<List<Movie>>()
    val listPopularMovies: LiveData<List<Movie>> get() = _listPopularMovies

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String> get() = _showMessage


    fun requestPopularMovies(region: String) {

        viewModelScope.launch(Dispatchers.Main) {
            _progressVisible.value = true
            _listPopularMovies.value = loadPopularMoviesUseCase(region)
            _progressVisible.value = false
        }
    }

    fun onMovieClicked(movie: Movie) {

        _showMessage.value = movie.title
    }
}