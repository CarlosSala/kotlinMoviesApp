package com.example.moviesapp.framework.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.usecases.LoadPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest1 {

    private lateinit var loadPopularMoviesUseCase: LoadPopularMoviesUseCase
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        loadPopularMoviesUseCase = mock(LoadPopularMoviesUseCase::class.java)
        mainViewModel = MainViewModel(loadPopularMoviesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created and request Popular Movies`() = runTest {
        // Given
        val fakeList: Flow<List<Movie>> = flowOf(
            listOf(
                Movie(
                    true,
                    "",
                    emptyList(),
                    1,
                    "",
                    "",
                    "",
                    1.0,
                    "",
                    "",
                    "",
                    false,
                    1.0,
                    1
                )
            )
        )

        `when`(loadPopularMoviesUseCase.invoke("")).thenReturn(fakeList)

        // When
        mainViewModel.requestPopularMovies("")

        // Then
        val result = mainViewModel.listPopularMovies.first()
        val expected = fakeList.first()

        assertEquals(expected, result)
    }
}
