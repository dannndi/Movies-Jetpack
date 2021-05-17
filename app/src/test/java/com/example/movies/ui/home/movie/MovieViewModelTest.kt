package com.example.movies.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.TestCoroutineRule
import com.example.movies.data.model.Movie
import com.example.movies.data.repo.TmdbRepo
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: TmdbRepo

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
        viewModel.getMovies()
    }

    @Test
    fun getMovie() {
        testCoroutineRule.runBlockingTest {
            viewModel.movieList.observeForever(observer)
            val movie = viewModel.movieList.value
            verify(repository).getNowPlayingMoviesFromServer()
            verify(observer).onChanged(movie)
        }
    }
}